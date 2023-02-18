package com.example.firebaseimageupload;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.badge.BadgeUtils;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button camera, gallery;
    ImageView imageView;
    final private int GALLEY_REQ_CODE = 1000;
    final private int CAMERA_REQ_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.gallery);
        imageView = findViewById(R.id.imageView);

        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.camera) {
            System.out.println("Camera button Clcked");
            Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(iCamera, CAMERA_REQ_CODE);

        } else if (v.getId() == R.id.gallery) {
            System.out.println("Gallery  button Clcked");

            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLEY_REQ_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLEY_REQ_CODE) {
                //for gallery
                imageView.setImageURI(data.getData());
            }
            if (requestCode == CAMERA_REQ_CODE) {
                Bitmap img = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(img);

            }
        }
    }
}