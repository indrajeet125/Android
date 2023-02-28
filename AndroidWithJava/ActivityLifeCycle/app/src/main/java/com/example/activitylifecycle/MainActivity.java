package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("OnCreate  method called......");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,result.class);
        startActivity(intent);
    }



    protected void onStart() {
        System.out.println("onStart method called......");
        super.onStart();
    }

    protected void onResume() {
        System.out.println("onResume(); method called......");
        super.onResume();
    }

    protected void onPause() {
        System.out.println("onPause method called......");
        super.onPause();
    }

    protected void onStop() {
        System.out.println("onStop method called......");
        super.onStop();
    }

    protected void onRestart() {
        System.out.println("onRestart method called......");
        super.onRestart();
    }
    protected void onDestroy() {
        System.out.println("onDestroy method called......");
        super.onDestroy();
    }






}