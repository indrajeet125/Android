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
    EditText rSch_id, rName, rMobile, rEmail, rDistrict, rState, rpassword;
    RadioGroup rgenderGroup;
    RadioButton rGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        rSch_id = findViewById(R.id.scholarId);
        rpassword = findViewById(R.id.spassword);
        rName = findViewById(R.id.sName);
        rgenderGroup = findViewById(R.id.sgenderRadioGroup);
        rMobile = findViewById(R.id.sMobile);
        rEmail = findViewById(R.id.sEmail);
        rDistrict = findViewById(R.id.sDistrict);
        rState = findViewById(R.id.sState);
//        rReset = findViewById(R.id.sReset);
        findViewById(R.id.sSubmit);


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
        rpassword.setText("");
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
        String name, gender, mobile, email, district, state, psword;
        String rsc_id = rSch_id.getText().toString();
        if (rsc_id.length() != 3) {
            Toast.makeText(this, "enter valid Scholer ID 3digit ", Toast.LENGTH_SHORT).show();
            return;
        }
        psword = rpassword.getText().toString();
        if (psword.length() < 6) {
            Toast.makeText(this, "enter password valid min 6 length ", Toast.LENGTH_SHORT).show();
            return;
        }
        name = rName.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "Enter Name ", Toast.LENGTH_SHORT).show();
            return;
        }

        gender = rGender.getText().toString();
        mobile = rMobile.getText().toString();

        if (mobile.length() != 10) {
            Toast.makeText(this, "Enter valid 10 digit mobile number  ", Toast.LENGTH_SHORT).show();
            return;
        }

        email = rEmail.getText().toString();
        if (email.length() == 0) {
            Toast.makeText(this, "Enter valid email address  ", Toast.LENGTH_SHORT).show();
            return;
        }

        district = rDistrict.getText().toString();
        if (district.length() == 0) {
            Toast.makeText(this, "Enter valid district  ", Toast.LENGTH_SHORT).show();
            return;
        }
        state = rState.getText().toString();
        if (state.length() == 0) {
            Toast.makeText(this, "Enter valid state ", Toast.LENGTH_SHORT).show();
            return;
        }

        Api db = new Api(this);
        sch_id = Integer.parseInt(rsc_id);
        boolean check = db.addStudent(new Student(sch_id, psword, name, gender, mobile, email, district, state, false));

        if (check) {
            Toast.makeText(this, "data added successfully ", Toast.LENGTH_SHORT).show();
       reserField();
        } else Toast.makeText(this, "failed to add  ", Toast.LENGTH_SHORT).show();
        for (Student student : db.getAllStudent()) {
            System.out.println(student);
        }
    }


}