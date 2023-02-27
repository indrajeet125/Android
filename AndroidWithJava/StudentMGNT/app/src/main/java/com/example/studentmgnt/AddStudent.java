package com.example.studentmgnt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmgnt.Backend.API.Api;
import com.example.studentmgnt.model.Student;

public class AddStudent extends AppCompatActivity {
    TextView rHeading;
    Button rReset, rSubmit;//r(register submit)
    EditText rSch_id, rName, rMobile, rEmail, rDistrict, rState;
    RadioGroup rgenderGroup;
    RadioButton rGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        rSch_id = findViewById(R.id.scholarId);
        rName = findViewById(R.id.sName);
        rgenderGroup = findViewById(R.id.genderRadioGroup);
        rMobile = findViewById(R.id.sMobile);
        rEmail = findViewById(R.id.sEmail);
        rDistrict = findViewById(R.id.sDistrict);
        rState = findViewById(R.id.sState);
        rReset = findViewById(R.id.sReset);


        rgenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rGender = group.findViewById(checkedId);
                System.out.println(rGender.getText() + "..............");


            }

        });


    }

    void reserField() {
        System.out.println("Reset btn called ...............");

        rSch_id.setText("");
        rName.setText("");
//        rgenderGroup.clearCheck();//not working


        rMobile.setText("");
        rEmail.setText("");
        rDistrict.setText("");
        rState.setText("");
    }


    public void btnReset(View view) {
        System.out.println("reset  btn called ...............");


        reserField();
    }

    public void btnSubmit(View view) {
        // future do some validation
        System.out.println("submit  btn called ...............");


        int sch_id;
        String name, gender, mobile, email, district, state;
        sch_id = Integer.parseInt(rSch_id.getText().toString());
        name = rName.getText().toString();
        gender = rGender.getText().toString();
        mobile = rMobile.getText().toString();
        email = rEmail.getText().toString();
        district = rDistrict.getText().toString();
        state = rState.getText().toString();

        Api db = new Api(this);
        boolean check = db.addStudent(new Student(sch_id, name, gender, mobile, email, district, state, false));
        if (check)
            Toast.makeText(this, "data added successfully ", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "failed to add  ", Toast.LENGTH_SHORT).show();
        for (Student student : db.getAllStudent()) {
            System.out.println(student);

        }


    }


}