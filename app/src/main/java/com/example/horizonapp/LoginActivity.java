package com.example.horizonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPassword;
    Button btn;
    TextView tv;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        edEmail = findViewById(R.id.editTextLoginEmailAddress);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);
        progressBar = findViewById(R.id.progressBar);

        tv.setOnClickListener(task -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        btn.setOnClickListener(v -> {
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();

            userLogin(email, password);
            userAuth(email, password);
        });
    }

    private void userLogin(String email, String password) {

        if (email.isEmpty()) {
            edEmail.setError("Email is required");
            edEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Enter a valid Email");
            edEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            edPassword.setError("Password is required");
            edPassword.requestFocus();
            return;
        }
        if (password.length() < 8) {
            edPassword.setError("Password length must be at least 8 characters");
            edPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    private void userAuth(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        mUser = mAuth.getCurrentUser();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}




