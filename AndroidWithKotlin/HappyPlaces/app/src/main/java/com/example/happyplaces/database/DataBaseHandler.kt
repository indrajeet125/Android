package com.example.happyplaces.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.happyplaces.models.HappyPlaceModel
import java.sql.SQLException

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
        db.close()
        return result
    }

    fun updateHappyPlace(happyplace: HappyPlaceModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_TITLE, happyplace.title)
        contentValues.put(KEY_IMAGE, happyplace.image)
        contentValues.put(KEY_DESCRIPTION, happyplace.description)
        contentValues.put(KEY_DATE, happyplace.date)
        contentValues.put(KEY_LOCATION, happyplace.location)
        contentValues.put(KEY_LATITUDE, happyplace.latitude)
        contentValues.put(KEY_LONGITUDE, happyplace.longitude)

        var success = db.update(TABLE_HAPPY_PLACE,
            contentValues,
            KEY_ID + "=" + happyplace.id,null)
        db.close()
        return success
    }

    fun deleteHappyPlace(happyplace: HappyPlaceModel):Int{
        val db=this.writableDatabase
        val success=db.delete(TABLE_HAPPY_PLACE, KEY_ID+"="+happyplace.id,null)
        db.close()
        return success
    }
    fun getHappyPlacesList(): ArrayList<HappyPlaceModel> {
        val happyPlacesList = ArrayList<HappyPlaceModel>()
        val SELECTQUERY = "SELECT * FROM $TABLE_HAPPY_PLACE"
        val db = this.readableDatabase
        try {
            val c: Cursor = db.rawQuery(SELECTQUERY, null)
            if (c.moveToFirst()) {
                do {

                    val place = HappyPlaceModel(
                        c.getInt(c.getColumnIndexOrThrow(KEY_ID)),
                        c.getString(c.getColumnIndexOrThrow(KEY_TITLE)),
                        c.getString(c.getColumnIndexOrThrow(KEY_IMAGE)),
                        c.getString(c.getColumnIndexOrThrow(KEY_DESCRIPTION)),
                        c.getString(c.getColumnIndexOrThrow(KEY_DATE)),
                        c.getString(c.getColumnIndexOrThrow(KEY_LOCATION)),
                        c.getDouble(c.getColumnIndexOrThrow(KEY_LATITUDE)),
                        c.getDouble(c.getColumnIndexOrThrow(KEY_LONGITUDE))
                    )
                    happyPlacesList.add(place)
                } while (c.moveToNext())
            }
            c.close()

        } catch (e: SQLException) {
            db.execSQL(SELECTQUERY)
            return ArrayList();
        }

        return happyPlacesList;
    }
}