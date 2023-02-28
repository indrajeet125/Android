package com.example.studentmgnt.Backend.API;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.widget.TintableCheckedTextView;

import com.example.studentmgnt.Backend.DBInfo.BackendInfo;
import com.example.studentmgnt.Backend.TableInfo.Table;
import com.example.studentmgnt.model.Student;

import java.net.PortUnreachableException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Api extends SQLiteOpenHelper {
    public static String SELECT_ALL_STUDENT = "SELECT * FROM " + BackendInfo.table_name;

    public Api(@Nullable Context context) {
        super(context, BackendInfo.DB_NAME, null, BackendInfo.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        BackendInfo.printLog("database going create ");

        String create2 = "CREATE TABLE " + BackendInfo.table_name + "(" +

                Table.SCH_ID + "  INTEGER PRIMARY KEY," +
                Table.PASSWORD + " TEXT, " +
                Table.NAME + " TEXT, " +
                Table.GENDER + " TEXT, " +
                Table.MOBILE + " TEXT, " +
                Table.EMAIL + " TEXT, " +
                Table.DISTRICT + " TEXT," +
                Table.STATE + " TEXT, " +
                Table.ISADMIN + " INTEGER" +

                ")";
        BackendInfo.printLog("query formed ");


        db.execSQL(create2);
        BackendInfo.printLog("database   created successfully ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public boolean addStudent(Student student) {
        boolean check = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        System.out.println(student);
//
        values.put(Table.SCH_ID, student.getSch_id());
        values.put(Table.PASSWORD, student.getPasword());
        values.put(Table.NAME, student.getName());
        values.put(Table.GENDER, student.getGender());
        values.put(Table.MOBILE, student.getMobile());
        values.put(Table.EMAIL, student.getEmail());
        values.put(Table.DISTRICT, student.getDistrict());
        values.put(Table.STATE, student.getState());
        values.put(Table.ISADMIN, student.isAdmin());
        try {
            db.insertOrThrow(
                    BackendInfo.table_name, null, values);
            BackendInfo.printLog("Data inserted successfully ");
            check = true;

        } catch (Exception e) {
            BackendInfo.printLog("Data insertion failed  ");
        }


        db.close();
        return check;

    }

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SELECT_ALL_STUDENT, null);
        while (cursor.moveToNext()) {
            Student student = new Student();
            student.setSch_id(Integer.parseInt(cursor.getString(0)));
            student.setPasword(cursor.getString(1));
            student.setName(cursor.getString(2));
            student.setGender(cursor.getString(3));
            student.setMobile(cursor.getString(4));
            student.setEmail(cursor.getString(5));
            student.setDistrict(cursor.getString(6));
            student.setState(cursor.getString(7));
            student.setAdmin(Boolean.parseBoolean(cursor.getString(8)));
            students.add(student);
        }
        BackendInfo.printLog("data fetched successfully ");

        db.close();
        return students;

    }

    //    delete(String table, String whereClause, String[] whereArgs) {
    public int deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedROW = db.delete(BackendInfo.table_name, Table.SCH_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        if (deletedROW > 0)
            BackendInfo.printLog("delete successfully");
        else BackendInfo.printLog("delete failed ");
        return deletedROW;

    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Table.SCH_ID, student.getSch_id());
        values.put(Table.NAME, student.getName());
        values.put(Table.GENDER, student.getGender());
        values.put(Table.MOBILE, student.getMobile());
        values.put(Table.EMAIL, student.getEmail());
        values.put(Table.DISTRICT, student.getDistrict());
        values.put(Table.STATE, student.getState());
        values.put(Table.ISADMIN, student.isAdmin());

        int a = db.update(BackendInfo.table_name, values, Table.SCH_ID + "=?", new String[]{String.valueOf(student.getSch_id())});
        db.close();
        if (a > 0)
            BackendInfo.printLog("data updated successfully ");
        else BackendInfo.printLog("data updated failed  ");

        return a;

    }

    public int getNUmberOfRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        int a = db.rawQuery(SELECT_ALL_STUDENT, null).getCount();
        db.close();
        return a;
    }


}
