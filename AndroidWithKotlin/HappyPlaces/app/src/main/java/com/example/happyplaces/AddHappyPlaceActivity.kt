package com.example.happyplaces

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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.SettingsClickListener
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

    var et_date: AppCompatEditText? = null
    var iv_place_image: AppCompatImageView? = null
    private var cal = Calendar.getInstance()
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_happy_place)

        val toolbar_add_place = findViewById<Toolbar>(R.id.toolbar_add_place)
        et_date = findViewById(R.id.et_date)
        val btn_save: Button = findViewById(R.id.btn_save)
        var tv_add_image: TextView = findViewById(R.id.tv_add_image)
        iv_place_image = findViewById(R.id.iv_place_image)

        //back button
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

        et_date?.setOnClickListener(this)
        tv_add_image.setOnClickListener(this)
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

                    var saveImageToInternalStorage = saveImageToInternalStorage(selectedImageBitMap)
                    Log.e("Saved Image", "path:: $saveImageToInternalStorage")

                }
            } else if (requestCode == CAMERA_CODE) {
                var thumbNail: Bitmap = data!!.extras!!.get("data") as Bitmap
                iv_place_image?.setImageBitmap(thumbNail)

                var saveImageToInternalStorage = saveImageToInternalStorage(thumbNail)
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