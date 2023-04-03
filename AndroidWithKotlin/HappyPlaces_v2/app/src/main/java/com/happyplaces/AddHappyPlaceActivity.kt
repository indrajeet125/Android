package com.happyplaces

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_happy_place.*

class AddHappyPlaceActivity : AppCompatActivity() {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)

        // This is used to align the xml view to this class
        setContentView(R.layout.activity_add_happy_place)

        // TODO (Step 2: Setting up the action bar using the toolbar and making enable the home back button and also adding the click of it.)
        // START
        setSupportActionBar(toolbar_add_place) // Use the toolbar to set the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // This is to use the home back button.
        // Setting the click event to the back button
        toolbar_add_place.setNavigationOnClickListener {
            onBackPressed()
        }
        // END
    }
}