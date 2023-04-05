package com.example.happyplaces.activity

import SwipeToDeleteCallback
import SwipeToEditCallback
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.happyplaces.R
import com.example.happyplaces.adapters.HappyPlaceAdapter
import com.example.happyplaces.database.DataBaseHandler
import com.example.happyplaces.models.HappyPlaceModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ADD_PLACE_ACTIVITY_REQUEST_CODE = 1
        var EXTRA_PLACE_DETAILS = "extra_place_details"
    }

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
            startActivityForResult(intent, ADD_PLACE_ACTIVITY_REQUEST_CODE)
        }

        getHappyPlacesFromLocalDataBase()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PLACE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                getHappyPlacesFromLocalDataBase()
            } else {
                Log.e("Activity", "back or cancel the operation ")
            }
        }
    }

    fun setUpHappyPlacesRecyclerView(getHappyPlaceList: ArrayList<HappyPlaceModel>) {

        rv_happyplaces_list?.layoutManager = LinearLayoutManager(this)
        rv_happyplaces_list?.setHasFixedSize(true)
        val placesAdapter = HappyPlaceAdapter(this, getHappyPlaceList)
        println(rv_happyplaces_list)
        rv_happyplaces_list?.adapter = placesAdapter

        placesAdapter.setOnClickListener(object : HappyPlaceAdapter.OnClickListener {
            override fun onClick(position: Int, model: HappyPlaceModel) {
                val intent = Intent(this@MainActivity, HappyPlaceDetailsActivity::class.java)
                intent.putExtra(EXTRA_PLACE_DETAILS, model)
                startActivity(intent)
            }

        })

        val editSwipeHandler = object : SwipeToEditCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rv_happyplaces_list?.adapter as HappyPlaceAdapter
                adapter.notifyEditItem(
                    this@MainActivity,
                    viewHolder.adapterPosition,
                    ADD_PLACE_ACTIVITY_REQUEST_CODE
                )
            }
        }
        val editItemTouchHelper = ItemTouchHelper(editSwipeHandler)
        editItemTouchHelper.attachToRecyclerView(rv_happyplaces_list)
        //delete
        val deleteSwipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rv_happyplaces_list?.adapter as HappyPlaceAdapter
                adapter.RemoveAt(viewHolder.adapterPosition)
                getHappyPlacesFromLocalDataBase()
            }
        }
        val deleteItemTouchHelper = ItemTouchHelper(deleteSwipeHandler)
        deleteItemTouchHelper.attachToRecyclerView(rv_happyplaces_list)

    }

    private fun getHappyPlacesFromLocalDataBase() {
        val dbHandler = DataBaseHandler(this)
        var getHappyPlaceList: ArrayList<HappyPlaceModel> = dbHandler.getHappyPlacesList()
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