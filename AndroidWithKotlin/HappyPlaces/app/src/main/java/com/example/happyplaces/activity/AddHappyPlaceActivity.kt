package com.example.happyplaces.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import com.example.happyplaces.R
import com.example.happyplaces.database.DataBaseHandler
import com.example.happyplaces.models.HappyPlaceModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddHappyPlaceActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val GALLERY = 1
        private const val CAMERA_CODE = 2
        private const val IMAGE_DIRECTORY = "HappyPlaces"
    }

    var et_title: EditText? = null
    var et_description: EditText? = null
    var et_date: AppCompatEditText? = null
    var et_location: EditText? = null

    var iv_place_image: AppCompatImageView? = null


    private var cal = Calendar.getInstance()
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    private var saveImageToInternalStorage: Uri? = null
    private var mLatitude: Double = 0.0
    private var mLongitude: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_happy_place)


        et_title = findViewById(R.id.et_title)
        et_description = findViewById(R.id.et_description)
        et_date = findViewById(R.id.et_date)
        et_location = findViewById(R.id.et_location)
        iv_place_image = findViewById(R.id.iv_place_image)
        var tv_add_image: TextView = findViewById(R.id.tv_add_image)
        val btn_save: Button = findViewById(R.id.btn_save)


        //back button
        val toolbar_add_place = findViewById<Toolbar>(R.id.toolbar_add_place)
        setSupportActionBar(toolbar_add_place) // Use the toolbar to set the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // This is to use the home back button.
        toolbar_add_place.setNavigationOnClickListener {
            onBackPressed()
        }
        //date picker dialog
        dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        updateDateInView()

        et_date?.setOnClickListener(this)
        tv_add_image.setOnClickListener(this)
        btn_save.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.et_date -> {
                DatePickerDialog(
                    this@AddHappyPlaceActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            R.id.tv_add_image -> {
                var pictureDialog = AlertDialog.Builder(this)
                pictureDialog.setTitle("select Option ")

                var pickerDialogItems =
                    arrayOf("select Photo from gallery", "caption photo from camera")

                pictureDialog.setItems(pickerDialogItems) { dialog, which ->
                    when (which) {
                        0 -> choosePhotoFromGallery()
                        1 -> takePhotoFromCamera()
                    }
                }
                pictureDialog.show()
            }
            R.id.btn_save -> {
                when {
                    et_title?.text.isNullOrEmpty() -> {
                        Toast.makeText(this, "please enter title", Toast.LENGTH_SHORT).show()
                    }
                    et_description?.text.isNullOrEmpty() -> {
                        Toast.makeText(this, "please enter description ", Toast.LENGTH_SHORT).show()
                    }
                    et_location?.text.isNullOrEmpty() -> {
                        Toast.makeText(this, "please enter location", Toast.LENGTH_SHORT).show()
                    }
                    saveImageToInternalStorage == null -> {
                        Toast.makeText(this, "please select an image ", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        val happyPlaceModel = HappyPlaceModel(
                            0,
                            et_title?.text.toString(),
                            saveImageToInternalStorage.toString(),
                            et_description?.text.toString(),
                            et_date?.text.toString(),
                            et_location?.text.toString(),
                            mLatitude,
                            mLongitude
                        )
                        val dbHandler = DataBaseHandler(this)
                        val addHappyPlace = dbHandler.addHappyPlace(happyPlaceModel)
                        if (addHappyPlace > 0) {
                            Toast.makeText(this, "data added to dataBase ", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }
                    }
                }


            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY) {
                if (data != null) {
                    val contentURI = data.data
                    val selectedImageBitMap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI);
                    iv_place_image?.setImageBitmap((selectedImageBitMap))

                    saveImageToInternalStorage = saveImageToInternalStorage(selectedImageBitMap)
                    Log.e("Saved Image", "path:: $saveImageToInternalStorage")

                }
            } else if (requestCode == CAMERA_CODE) {
                var thumbNail: Bitmap = data!!.extras!!.get("data") as Bitmap
                iv_place_image?.setImageBitmap(thumbNail)

                saveImageToInternalStorage = saveImageToInternalStorage(thumbNail)
                Log.e("Saved Image", "path:: $saveImageToInternalStorage")

            }
        }
    }

    private fun takePhotoFromCamera() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {
                        Toast.makeText(
                            this@AddHappyPlaceActivity,
                            "Camera permission granted ",
                            Toast.LENGTH_SHORT
                        ).show()
                        val galaryIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(galaryIntent, CAMERA_CODE)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    showRationalDialogForPermissions()
                }
            }).onSameThread().check()

    }

    private fun choosePhotoFromGallery() {

        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {
                        Toast.makeText(
                            this@AddHappyPlaceActivity,
                            "Storage permission granted ",
                            Toast.LENGTH_SHORT
                        ).show()
                        val galaryIntent = Intent(Intent.ACTION_PICK)
                        startActivityForResult(galaryIntent, GALLERY)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    showRationalDialogForPermissions()
                }
            }).onSameThread().check()
    }

    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this).setMessage(
            "it looks like you have turned off permission required"
                    + "for this feature. it can ve enabled under the" +
                    " Applications Settings "
        ).setPositiveButton("got to settings") { _, _ ->
            try {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                var uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }.setNegativeButton("Cancle") { dialog, _ ->
            dialog.dismiss()

        }.show()
    }

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())

        et_date?.setText(sdf.format(cal.time).toString())

    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): Uri {

        var wrapper = ContextWrapper(applicationContext)
        var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")
        try {
            var stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Uri.parse(file.absolutePath)
    }

}