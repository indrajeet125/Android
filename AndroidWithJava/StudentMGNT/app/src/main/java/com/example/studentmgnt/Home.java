package com.example.studentmgnt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void viewallstudent(View view) {
        Intent intent = new Intent(this, RecyCleStudents.class);
        startActivity(intent);
    }

    //
    public void addStudent(View view) {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }

    public void logout(View view) {
//        SharedPreferences preferences=getPreferences("LOGIN",MODE_PRIVATE);
        //do from here
    }
}