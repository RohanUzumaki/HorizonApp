package com.example.horizonapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPassword;
    Button btn;
    CheckBox checkBox;
    TextView tv, forgotpassword;
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
        checkBox = findViewById(R.id.checkBox);
        forgotpassword = findViewById(R.id.forgotPass);

        // Restore "Remember me" checkbox state from shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        checkBox.setChecked(sharedPreferences.getBoolean("rememberMe", false));

        btn.setOnClickListener(v -> {
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();

            if (userLogin(email, password)) {
                userAuth(email, password, checkBox.isChecked());
            }
        });

        tv.setOnClickListener(task -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        forgotpassword.setOnClickListener(task -> startActivity(new Intent(LoginActivity.this, ForgotPassword.class)));
    }

    private boolean userLogin(String email, String password) {

        if (email.isEmpty()) {
            edEmail.setError("Email is required");
            edEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Enter a valid Email");
            edEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            edPassword.setError("Password is required");
            edPassword.requestFocus();
            return false;
        }

        if (password.length() < 8) {
            edPassword.setError("Password length must be at least 8 characters");
            edPassword.requestFocus();
            return false;
        }

        return true;
    }

    private void userAuth(String email, String password, boolean rememberMe) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email and password are required.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        mUser = mAuth.getCurrentUser();
                        if (rememberMe) {
                            // Save "Remember me" checkbox state to shared preferences
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                            sharedPreferences.edit().putBoolean("rememberMe", true).apply();
                        }
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
