package com.example.displaymarks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayMarks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_marks);

        Intent intent = getIntent();


        TextView gradeView = findViewById(R.id.grade);
        TextView percentageView = findViewById(R.id.percentage);

        int s1 = Integer.parseInt(intent.getStringExtra("s1"));
        int s2 = Integer.parseInt(intent.getStringExtra("s2"));
        int s3 = Integer.parseInt(intent.getStringExtra("s3") );


        System.out.println(s1+" "+s2+" "+s3+"\n\n");


        int percentage = (s1 + s2 + s3) / 3;
        percentageView.setText(percentage + "");



        final  String grade1;

        if (percentage >= 90)
            grade1 = "A+";
        else if (percentage >= 80) grade1 = "A";
        else if (percentage >= 70) grade1 = "B+";
        else if (percentage >= 60) grade1 = "B";
        else if (percentage >= 50) grade1 = "C+";
        else if (percentage >= 40) grade1 = "C";
        else grade1 = "F";


        gradeView.setText(grade1);
    }

}