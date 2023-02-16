package com.example.displaymarks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String MSG = "com.example.displaymarks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nametext = findViewById(R.id.sub3);
        String name = nametext.getText().toString();

    }

    public void showResult(View view) {
        EditText t1 = findViewById(R.id.sub1);
        EditText t2 = findViewById(R.id.sub2);
        EditText t3 = findViewById(R.id.sub3);

        String s1 = (t1.getText().toString());
        String s2 = (t2.getText().toString());
        String s3 = (t3.getText().toString());

        Intent intent = new Intent(this, DisplayMarks.class);

        intent.putExtra("s1", s1);
        intent.putExtra("s2", s2);
        intent.putExtra("s3", s3);

        this.startActivity(intent);

    }
}