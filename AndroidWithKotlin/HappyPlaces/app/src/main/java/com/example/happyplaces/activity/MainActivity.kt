package com.example.happyplaces.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplaces.R
import com.example.happyplaces.adapters.HappyPlaceAdapter
import com.example.happyplaces.database.DataBaseHandler
import com.example.happyplaces.models.HappyPlaceModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {


    var rv_happyplaces_list: RecyclerView? = null
    var tv_no_records_available: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabAddHappyPlace = findViewById(R.id.fabAddHappyPlace) as FloatingActionButton
        rv_happyplaces_list = findViewById(R.id.rv_happyplaces_list)
        tv_no_records_available = findViewById(R.id.tv_no_records_available)

        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }

        getHappyPlacesFromLocalDataBase()
    }

    fun setUpHappyPlacesRecyclerView(getHappyPlaceList: ArrayList<HappyPlaceModel>) {

        rv_happyplaces_list?.layoutManager = LinearLayoutManager(this)
        rv_happyplaces_list?.setHasFixedSize(true)
        val placesAdapter = HappyPlaceAdapter(this, getHappyPlaceList)
        println(rv_happyplaces_list)
        rv_happyplaces_list?.adapter = placesAdapter


    }

    private fun getHappyPlacesFromLocalDataBase() {
        val dbHandler = DataBaseHandler(this)
        var getHappyPlaceList: ArrayList<HappyPlaceModel> = dbHandler.getHappyPlcesList()
        if (getHappyPlaceList.size > 0) {

            rv_happyplaces_list?.visibility = View.VISIBLE
            tv_no_records_available?.visibility = View.GONE
            setUpHappyPlacesRecyclerView(getHappyPlaceList)

        } else {

            rv_happyplaces_list?.visibility = View.GONE
            tv_no_records_available?.visibility = View.VISIBLE

        }


    }
}