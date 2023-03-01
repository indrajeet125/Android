package com.example.studentmgnt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmgnt.Backend.API.Api;
import com.example.studentmgnt.model.Student;

import java.util.List;

public class Home extends AppCompatActivity {

    EditText eid;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        eid = findViewById(R.id.idForOperation);
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
        SharedPreferences preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("islogin", false);
        editor.apply();

        Intent inext = new Intent(this, login.class);
        startActivity(inext);
        finish();


    }

    public void deleteStudent(View view) {
        Api db = new Api(this);
        String sid = eid.getText().toString();
        if (sid.equals("")) {
            Toast.makeText(this, "please enter id ", Toast.LENGTH_SHORT).show();
            return;
        }
        id = Integer.parseInt(sid);
        if (db.deleteById(id)) {
            eid.setText("");
            Toast.makeText(this, "id : " + id + "deleted successfully ", Toast.LENGTH_SHORT).show();
            return;

        } else Toast.makeText(this, "id : " + id + " Not exits ", Toast.LENGTH_SHORT).show();


    }

    public void updateStudent(View view) {
        Api db = new Api(this);
        boolean exits = false;
        String sid = eid.getText().toString();
        if (sid.equals("")) {
            Toast.makeText(this, "please enter id ", Toast.LENGTH_SHORT).show();
            return;
        }
        id = Integer.parseInt(sid);
        List<Student> students = db.getAllStudent();
        Student student = null;
        for (int i = 0; i < students.size(); i++) {
            student = students.get(i);
//            System.out.println(student);
            if (student.getSch_id() == id) {
                exits = true;
                break;
            }
        }
        if (exits == false) {
            Toast.makeText(this, "id: " + id + " not exitst ", Toast.LENGTH_SHORT).show();
            return;

        } else {


            Intent intent = new Intent(this, updatestudnetdetails.class);
            intent.putExtra("id", student.getSch_id() );
            startActivity(intent);

        }

    }
}