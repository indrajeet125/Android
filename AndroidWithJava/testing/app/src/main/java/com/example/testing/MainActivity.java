package com.example.testing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements View.OnClickListener {
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RadioGroupActivity");
        mRadioGroup = (RadioGroup) findViewById(R.id.menu);
        Button clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(this);
    }



    public void onClick(View v) {
        mRadioGroup.clearCheck();
    }
}