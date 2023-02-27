package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Spinner spinner;
    AutoCompleteTextView autotxtview;
    ArrayList<String> arrNames = new ArrayList<>();
    ArrayList<String> arrIds = new ArrayList<>();
    ArrayList<String> arrayLang = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listview);
        autotxtview = findViewById(R.id.autotxtview);

        {
            for (int i = 0; i < 19; i++) {
                arrNames.add(i + " ram");
            }

            ArrayAdapter<String> arrAdapterName = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrNames);
            listView.setAdapter(arrAdapterName);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "click on " + position + "   id" + id, Toast.LENGTH_SHORT).show();
                }
            });
        }
        {
            arrIds.add("adhar card");
            arrIds.add("pan card");
            arrIds.add("pan card");
            arrIds.add("adhar card");
            arrIds.add("pan card");
            arrIds.add("pan card");

            ArrayAdapter<String> arrayAdapterId = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrIds);
            spinner.setAdapter(arrayAdapterId);
        }


        {
            arrayLang.add("c");
            arrayLang.add("c++");
            arrayLang.add("java");
            arrayLang.add("R");
            arrayLang.add("Python");

            ArrayAdapter<String> arrayAdapterLang = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_2, arrayLang);

            autotxtview.setAdapter(arrayAdapterLang);
            autotxtview.setThreshold(1);
        }


    }
}