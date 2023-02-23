package com.example.recyclerviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ContactModel> arrContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecycleContact);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        for (int i = 0; i < 60; i++)
            arrContact.add(new ContactModel(R.drawable.ic_launcher_background, "AC", "" + (i + 1)));

        RecycleContactAdapter adapter = new RecycleContactAdapter(getApplicationContext(), arrContact);
        recyclerView.setAdapter(adapter);

    }


}