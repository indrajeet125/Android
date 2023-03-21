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
            System.out.println(studentList.get(i));
            if (studentList.get(i).getSch_id() == id && studentList.get(i).getPasword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void login(View view) {
        System.out.println("login clickerd ");
//        int x = 9, y = 0;
//        if (x > y) return;

        String rsc_id = eid.getText().toString();
        if (rsc_id.length() != 3) {
            Toast.makeText(this, "enter valid Scholer ID 3digit ", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = Integer.parseInt(rsc_id);
        String password = epassword.getText().toString();
        if (password.length() < 6) {
            Toast.makeText(this, "enter password valid min 6 length ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (checkCredential(id, password)) {
            SharedPreferences sharedPreferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("islogin", true);
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