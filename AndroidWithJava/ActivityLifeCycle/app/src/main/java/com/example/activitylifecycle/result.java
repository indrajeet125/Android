package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class result extends AppCompatActivity {


    protected void onDestroy(Bundle savedInstanceState) {
        super.onDestroy( );
        setContentView(R.layout.activity_result);
    }

}