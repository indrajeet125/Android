package com.example.studentmgnt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmgnt.Backend.API.Api;
import com.example.studentmgnt.model.Student;

import java.util.List;

public class updatestudnetdetails extends AppCompatActivity {
    TextView eid;
    EditText epassword, ename, emobile, eemail, edistrict, estate;
    RadioGroup egenderGroup;
    RadioButton eGender;
    int gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatestudnetdetails);

        eid = findViewById(R.id.uscholarId);
        epassword = findViewById(R.id.uspassword);
        ename = findViewById(R.id.usName);
        egenderGroup = findViewById(R.id.ugenderRadioGroup);
        emobile = findViewById(R.id.usMobile);
        eemail = findViewById(R.id.usEmail);
        edistrict = findViewById(R.id.usDistrict);
        estate = findViewById(R.id.usState);


        { //geting id form intent and find student snd set
            Intent intent = getIntent();
            int id = intent.getIntExtra("id", -1);
            Api db = new Api(this);
            List<Student> students = db.getAllStudent();
            Student student = null;
            for (int i = 0; i < students.size(); i++) {
                student = students.get(i);
                System.out.println(student + " id  " + id);
                if (student.getSch_id() == id) {
                    break;
                }
            }
            eid.setText(student.getSch_id() + "");
            epassword.setText(student.getPasword());
            ename.setText(student.getName());
            {

                if (student.getGender().equals("Male"))
                    id = R.id.usMale;
                else if (student.getGender().equals("Female"))
                    id = R.id.usFemale;
                else id = R.id.usOther;
                eGender = findViewById(gender);
                egenderGroup.check(id);

            }

            emobile.setText(student.getMobile());
            eemail.setText(student.getEmail());
            edistrict.setText(student.getDistrict());
            estate.setText(student.getState());
        }

        egenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                eGender = group.findViewById(checkedId);
                System.out.println(eGender.getText() + "..............");
            }
        });


    }

    void reserupdateField() {
        System.out.println("Reset btn called ...............");
//id nhi karna h
        epassword.setText("");
        ename.setText("");
//        egenderGroup.clearCheck();
        emobile.setText("");
        eemail.setText("");
        edistrict.setText("");
        estate.setText("");
        return;
    }

    public void btnupdateReset(View view) {
        System.out.println("reset  btn called ...............");
        reserupdateField();
        return;
    }

    public void btnupdateSubmit(View view) {
        int sch_id;
        String name, gender, mobile, email, district, state, psword;

        sch_id = Integer.parseInt(eid.getText().toString());
        psword = epassword.getText().toString();
        name = ename.getText().toString();
        gender = eGender.getText().toString();
        mobile = emobile.getText().toString();
        email = eemail.getText().toString();
        district = edistrict.getText().toString();
        state = estate.getText().toString();

        Api db = new Api(this);
        boolean check = db.updateStudent(new Student(sch_id, psword, name, gender, mobile, email, district, state, false));

        if (check)
            Toast.makeText(this, "data updated  successfully ", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "failed to update   ", Toast.LENGTH_SHORT).show();

        btnupdateReset(view);

    }

}