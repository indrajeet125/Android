package com.example.loginregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView username, passwordtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitData(View view) {
        username = findViewById(R.id.username);
        passwordtext = findViewById(R.id.password);

        String name = username.getText().toString();
        String password = passwordtext.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("Users");

        System.out.println(users.child("email"));
        System.out.println(users.child("email"));




        if(name.equals("root") && password.equals("root")){
            Intent intent = new Intent(this, welComepage.class);
            this.startActivities(new Intent[]{intent});
        }
        else {
            Intent intent = new Intent(this, ErrorPage.class);
            this.startActivities(new Intent[]{intent});
        }




    }

    public void gotoregisterpage(View view) {
        Intent intent = new Intent(this, Registration.class);
        this.startActivities(new Intent[]{intent});
    }
}