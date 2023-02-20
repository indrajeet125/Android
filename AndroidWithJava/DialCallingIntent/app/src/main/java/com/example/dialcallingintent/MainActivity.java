package com.example.dialcallingintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCall(View view) {
        System.out.println("call button clicked --------------n\n\n\n");
//        EditText dial=findViewById(R.id.number);
//        String number= (dial.getText()).toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel" + "5673543534"));
        startActivity(intent);


    }

    public void onDial(View view) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);

    }
}