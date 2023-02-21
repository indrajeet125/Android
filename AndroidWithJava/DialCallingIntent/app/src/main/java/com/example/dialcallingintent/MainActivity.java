package com.example.dialcallingintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCall(View view) {
        System.out.println("call button clicked --------------n\n\n\n");
        EditText dial = findViewById(R.id.number);
        String number = (dial.getText()).toString();
        if (number.length() != 10) {
            Toast.makeText(this, "Enter the correct Number  10 digit ", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));
        dial.setText("");
        startActivity(intent);


    }

    public void onDial(View view) {

        Intent intent = new Intent(Intent.ACTION_DIAL);

        startActivity(intent);

    }
}