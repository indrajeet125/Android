package com.example.imageupload;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.appsearch.StorageInfo;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button camera, gallery, upload, browse;
    ImageView imageView;
    Uri uri;

    final private int GALLEY_REQ_CODE = 1000;
    final private int CAMERA_REQ_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.gallery);
        imageView = findViewById(R.id.imageView);
        browse = findViewById(R.id.browse);
        upload = findViewById(R.id.upload);


        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFirebase();
            }
        });
    }

    private void uploadToFirebase() {
        ImageView imageView1 = findViewById(R.id.imageView);


        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("image1");
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        })
                .OpL
        ;



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
            iGallery.setType("image/*");

            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLEY_REQ_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap;
            if (requestCode == GALLEY_REQ_CODE) {
                //for gallery
                uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(uri + "uri \n\n\n\n\n");
                imageView.setImageBitmap(bitmap);
            }
            if (requestCode == CAMERA_REQ_CODE) {
                bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}