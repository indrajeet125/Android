package com.example.happyplaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fabAddHappyPlace = findViewById(R.id.fabAddHappyPlace) as FloatingActionButton
        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }

        var iv_simple_image: CircleImageView=findViewById(R.id.iv_simple_image)
        var iv_circluler_image:CircleImageView=findViewById(R.id.iv_circluler_image)

        iv_simple_image.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_action_add_24dp))
        iv_simple_image.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_action_add_24dp))

    }
}