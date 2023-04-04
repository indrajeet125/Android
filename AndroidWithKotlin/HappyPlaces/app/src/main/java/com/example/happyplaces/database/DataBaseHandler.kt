package com.example.happyplaces.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcelable.Creator
import com.example.happyplaces.models.HappyPlaceModel

class DataBaseHandler(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "HappyPlacesDatabase"
        private const val TABLE_HAPPY_PLACE = "HappyPlacesTable"

        //All the column values name
        private const val KEY_ID = "_id"
        private const val KEY_TITLE = "title"
        private const val KEY_IMAGE = "image"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_DATE = "date"
        private const val KEY_LOCATION = "location"
        private const val KEY_LATITUDE = "latitude"
        private const val KEY_LONGITUDE = "longitude"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_HAPPY_PLACE_TABLE = ("CREATE TABLE " + TABLE_HAPPY_PLACE + "(" +

                KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_IMAGE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_LOCATION + " TEXT,"
                + KEY_LATITUDE + " TEXT,"
                + KEY_LONGITUDE + " TEXT )")
        db?.execSQL((CREATE_HAPPY_PLACE_TABLE))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_HAPPY_PLACE")

        onCreate(db)
    }

    fun addHappyPlace(happyplace: HappyPlaceModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_TITLE, happyplace.title)
        contentValues.put(KEY_IMAGE, happyplace.image)
        contentValues.put(KEY_DESCRIPTION, happyplace.description)
        contentValues.put(KEY_DATE, happyplace.date)
        contentValues.put(KEY_LOCATION, happyplace.location)
        contentValues.put(KEY_LATITUDE, happyplace.latitude)
        contentValues.put(KEY_LONGITUDE, happyplace.longitude)

        var result = db.insert(TABLE_HAPPY_PLACE, null, contentValues)
        return result
    }
}