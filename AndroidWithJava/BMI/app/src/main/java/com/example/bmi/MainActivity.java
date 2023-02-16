package com.example.bmi;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare  variable
        LinearLayout llmain;
        EditText weight, feet, inches;
        Button btncalculate;
        TextView result;

        //assigning value
        llmain = findViewById(R.id.llmain);
        weight = findViewById(R.id.weight);
        feet = findViewById(R.id.feet);
        inches = findViewById(R.id.inches);
        btncalculate = findViewById(R.id.btncalculate);
        result = findViewById(R.id.result);

        //handle click on button
        btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wt = Integer.parseInt(weight.getText().toString());
                int ft = Integer.parseInt(feet.getText().toString());
                int inc = Integer.parseInt(inches.getText().toString());

                final double oneinches = 2.53;
                int totalinches = ft * 12 + inc;
                double centimeter = totalinches * oneinches;
                double totalMeter = centimeter / 100;
                double bmi = wt / (totalMeter * totalMeter);
                System.out.println(centimeter);

                final double low = 18.5;
                final double uptohealdhy = 24.9;
                final double uptooverWeight = 29.9;

                if (bmi <= low) {
                    result.setText("You're Underweight");
                    llmain.setBackgroundColor(getColor(R.color.Underweight));

                } else if (bmi > low && bmi <= uptohealdhy) {
                    result.setText("You're HealthyWeigh");
                    llmain.setBackgroundColor(getColor(R.color.healthy));
                } else if (bmi > uptohealdhy && bmi <= uptooverWeight) {
                    result.setText("You're Overweight");
                    llmain.setBackgroundColor(getColor(R.color.Overweight));
                } else {
                    result.setText("You're Obesity");
                    llmain.setBackgroundColor(getColor(R.color.Obesity));
                }

                result.append(" \nBMI Value : " + (float) bmi + " ");
            }
        });
    }
}