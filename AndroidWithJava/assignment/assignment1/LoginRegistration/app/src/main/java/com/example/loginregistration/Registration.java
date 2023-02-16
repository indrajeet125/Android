package com.example.loginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    TextView registrationNumberText, username, passwordtext, emailText;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void submitDataReegister(View view) {
        username = findViewById(R.id.username);
        passwordtext = findViewById(R.id.password);
        emailText = findViewById(R.id.email);
        registrationNumberText = findViewById(R.id.regstrationNo);

        String name = username.getText().toString().trim();
        String password = passwordtext.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String registrationNo = registrationNumberText.getText().toString().trim();

        if (registrationNo.isEmpty()) {
            registrationNumberText.setError("registrationNo is empty");
            registrationNumberText.requestFocus();
            return;
        } if (name.isEmpty()) {
            username.setError("user is empty");
            username.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordtext.setError("Enter the password");
            passwordtext.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordtext.setError("Length of the password should be more than 6");
            passwordtext.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            emailText.setError("Email is empty");
            emailText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Enter the valid email address");
            emailText.requestFocus();
            return;
        }
      FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("Users");
        users.child(registrationNo).setValue(new User(name,password,email));

    }

    public void gotoLoginPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivities(new Intent[]{intent});
    }

}