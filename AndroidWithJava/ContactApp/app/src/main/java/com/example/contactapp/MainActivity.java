package com.example.contactapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp.addapter.RecyclerViewAdapter;
import com.example.contactapp.data.MyDBHandler;
import com.example.contactapp.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerView initialization
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyDBHandler db = new MyDBHandler(MainActivity.this);//backend api

        // fetching all record from database
        List<Contact> contactList = db.getAllContacts();


        //user your recyclerview
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}