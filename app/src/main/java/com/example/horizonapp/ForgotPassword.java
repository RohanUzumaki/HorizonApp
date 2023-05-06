package com.example.horizonapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText forgotEmail;
    String email;
    Button btnForgotPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();

        forgotEmail = findViewById(R.id.forgotEmail);
        btnForgotPassword = findViewById(R.id.buttonForgotPass);

        btnForgotPassword.setOnClickListener(task -> {
            validateData();
        });
    }

    private void validateData() {
        email = forgotEmail.getText().toString().trim();
        if (email.isEmpty()) {
            forgotEmail.setError("Email is required");
            forgotEmail.requestFocus();
        } else {
            forgotPass();
        }
    }

    private void forgotPass() {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ForgotPassword.this, "Check your email", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(ForgotPassword.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}