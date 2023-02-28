package com.example.studentmgnt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmgnt.Backend.API.Api;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences pref = getSharedPreferences("LOGIN", MODE_PRIVATE);
        Boolean check = pref.getBoolean("islogin", false);
        Intent inext;
        if (check) {
            inext = new Intent(MainActivity.this, Home.class);

        } else {
            inext = new Intent(MainActivity.this, login.class);
        }
        startActivity(inext);








 
/*
 Api db = new Api(this);
1 insert work db.addStudent(new Student(3, "N", "G", "M", "E", "D", "S",false));
2.getAllStudent work
3.delete  work
4 get Count work
5 update work

 */
    }


}