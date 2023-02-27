package com.example.studentmgnt.Backend.DBInfo;

import android.util.Log;

public class BackendInfo {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "studentDataBase";
    public static String table_name = "studenttable";

    public static void printLog(String msg) {
        Log.d("databaseStudent", "......"+msg+"........");
    }
}
