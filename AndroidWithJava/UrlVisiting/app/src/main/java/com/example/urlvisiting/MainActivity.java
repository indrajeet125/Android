package com.example.urlvisiting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button visit;
EditText urltxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void visitUrl(View view) {
        urltxt=findViewById(R.id.urlfield);
        visit=findViewById(R.id.visitbtn);
        String urlOriginal= urltxt.getText().toString();

        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(urlOriginal));
        startActivities(new Intent[]{intent});

    }
}
