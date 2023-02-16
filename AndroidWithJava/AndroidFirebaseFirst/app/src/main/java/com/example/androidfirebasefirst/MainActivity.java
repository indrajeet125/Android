package com.example.androidfirebasefirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editTextRoll, editTextName, editDureation, editTextBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void process(View view) {
        editTextRoll = findViewById(R.id.rollN);
        editTextName = findViewById(R.id.Name);
        editDureation = findViewById(R.id.Duration);
        editTextBranch = findViewById(R.id.branch);

        int roll = Integer.parseInt(editTextRoll.getText().toString());
        String name = editTextName.getText().toString().trim();
        String Duration = editDureation.getText().toString().trim();
        String branch = editTextBranch.getText().toString().trim();


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference node = database.getReference("students");

        DataHolder dataHolder = new DataHolder(name, Duration, branch);
        node.child(String.valueOf(roll)).setValue(dataHolder);


        editTextBranch.setText("");
        editTextRoll.setText("");
        editDureation.setText("");
        editTextName.setText("");


        Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_SHORT).show();

    }

    public void goforUploadPageAction(View view) {

        Intent intent =new Intent(this,uploadImage.class);
        this.startActivities(new Intent[]{intent});
    }
}