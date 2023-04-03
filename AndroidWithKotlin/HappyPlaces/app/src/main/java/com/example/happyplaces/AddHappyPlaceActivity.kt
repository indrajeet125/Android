package com.example.happyplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class AddHappyPlaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_happy_place)

//        val toolbar_add_place = findViewById(R.id.toolbar_add_place) as Toolbar
//        setSupportActionBar(toolbar_add_place) // Use the toolbar to set the action bar.
//        supportActionBar?.setDisplayHomeAsUpEnabled(true) // This is to use the home back button.
//
//        toolbar_add_place.setNavigationOnClickListener {
//            onBackPressed()
//        }
    }
}