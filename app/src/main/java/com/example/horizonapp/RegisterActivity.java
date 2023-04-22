package com.example.horizonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText edEmail,edPassword,edFirstName,edLastName,edPhone,edConfirmPassword;
    Button btn;
    TextView tv;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://horizonapp-942dc-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("Patient");

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edEmail = findViewById(R.id.editTextRegEmailAddress);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        edFirstName = findViewById(R.id.editTextFirstName);
        edLastName = findViewById(R.id.editTextLastName);
        edPhone = findViewById(R.id.editTextPhone);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingUser);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        tv.setOnClickListener(task -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

        btn.setOnClickListener(task -> {
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();
            String confirm_password = edConfirmPassword.getText().toString().trim();
            String firstName = edFirstName.getText().toString().trim();
            String lastName = edLastName.getText().toString().trim();
            String phone = edPhone.getText().toString().trim();

            boolean valid = validInput(email, password, confirm_password, firstName, lastName, phone);
            if (!valid) {
                Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_SHORT).show();
            } else {
                SuccessListener listener = new SuccessListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                };
                listener.setOnSuccessListener(new Runnable() {
                    @Override
                    public void run() {
                        pushRecord(firstName, lastName, phone, email, listener.getUserId());
                    }
                });

                createUser(email, password, listener);
            }
        });



    }
    private boolean validInput(String email, String password, String confirm_password, String FirstName, String LastName, String Phone) {

        if (email.isEmpty()) {
            edEmail.setError("Email is required");
            edEmail.requestFocus();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Enter a valid Email");
            edEmail.requestFocus();
            return false;
        }
        else if (password.isEmpty()) {
            edPassword.setError("Password is required");
            edPassword.requestFocus();
            return false;
        }
        else if (password.length() < 8) {
            edPassword.setError("Password length must be at least 8 characters");
            edPassword.requestFocus();
            return false;
        }
        else if (FirstName.isEmpty()) {
            edFirstName.setError("First Name is required");
            edFirstName.requestFocus();
            return false;
        }
        else if (LastName.isEmpty()) {
            edLastName.setError("Last name is required");
            edLastName.requestFocus();
            return false;
        }
        else if (Phone.isEmpty()) {
            edPhone.setError("Phone Number is required");
            edPhone.requestFocus();
            return false;
        }
        else if (Phone.length() != 10) {
            edPhone.setError("Please enter valid phone number");
            edPhone.requestFocus();
            return false;
        }
        else if (!password.equals(confirm_password))
        {
            edConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }


    private void createUser(String email, String password, SuccessListener listener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                        listener.setUserId(userId);
                        listener.onSuccess();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        listener.onFailure(task.getException());
                    }
                });
    }




    private void pushRecord(String firstName, String lastName, String phone, String email, String userId) {
        Patient patient = new Patient(firstName, lastName, phone, email);
        myRef.child(userId).setValue(patient).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                Log.e("RegisterActivity", "Error writing document", task.getException());
            }
        });
    }

}