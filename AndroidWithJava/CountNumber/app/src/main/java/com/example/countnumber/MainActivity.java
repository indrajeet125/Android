package com.example.countnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button plusOne, minusOne;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plusOne = findViewById(R.id.plusOne);
        minusOne = findViewById(R.id.minusOne);

        plusOne.setOnClickListener(this);
        minusOne.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        textView = findViewById(R.id.counter);
        int c = Integer.parseInt(textView.getText().toString());
        if (v.getId()==R.id.plusOne)
            c++;
        else c--;
        textView.setText(c + "");


    }
}
