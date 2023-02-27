package com.example.studentmgnt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.studentmgnt.Backend.API.Api;

import com.example.studentmgnt.View.ReCyclerViewAdapterrr;
import com.example.studentmgnt.model.Student;

import java.util.List;

public class RecyCleStudents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_cle_students);
        RecyclerView recyclerView = findViewById(R.id.recycleStudentList);

        Api db = new Api(this);
        List<Student> studentList = db.getAllStudent();


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ReCyclerViewAdapterrr reCyclerViewAdapterrr = new ReCyclerViewAdapterrr(this, studentList);
        recyclerView.setAdapter(reCyclerViewAdapterrr);


    }
}