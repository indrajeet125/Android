package com.example.studentmgnt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class displayonestundentDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayonestundent_details);
        Intent intent = getIntent();


        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String mobile = intent.getStringExtra("mobile");
        String email = intent.getStringExtra("email");
        String district = intent.getStringExtra("district");
        String state = intent.getStringExtra("state");

        TextView eid, ename, egeder, emobil, eemail, edist, estate;

        eid = findViewById(R.id.idl);
        ename = findViewById(R.id.namel);
        egeder = findViewById(R.id.genderl);
        emobil = findViewById(R.id.mobilel);
        eemail = findViewById(R.id.emaill);
        edist = findViewById(R.id.districtl);
        estate = findViewById(R.id.statel);

        eid.setText(id);
        ename.setText(name);
        egeder.setText(gender);
        emobil.setText(mobile);
        eemail.setText(email);
        edist.setText(district);
        estate.setText(state);


    }
}