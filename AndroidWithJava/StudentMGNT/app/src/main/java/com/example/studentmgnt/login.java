package com.example.studentmgnt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmgnt.Backend.API.Api;
import com.example.studentmgnt.Backend.DBInfo.BackendInfo;
import com.example.studentmgnt.model.Student;

import java.util.List;

public class login extends AppCompatActivity {

    EditText eid, epassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eid = findViewById(R.id.LoginId);
        epassword = findViewById(R.id.lgPassword);


    }

    private boolean checkCredential(int id, String password) {
        Api db = new Api(this);
        List<Student> studentList = db.getAllStudent();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getSch_id() == id && studentList.get(i).getPasword() == password) {
                return true;
            }
        }
        return false;
    }

    public void login(View view) {
        int id = Integer.parseInt(eid.getText().toString());
        String password = epassword.getText().toString();

        if (checkCredential(id, password)) {
            SharedPreferences sharedPreferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(id+"", true);
            edit.apply();

            Intent inext = new Intent(this, Home.class);
            startActivity(inext);

            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Login failed ", Toast.LENGTH_SHORT).show();



    }

    public void goforRegister(View view) {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }
}