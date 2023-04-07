package com.example.weatherapp.activites

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.Constants
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.network.WeatherService
import com.google.android.gms.location.*
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mProgressDialog: Dialog? = null
    private var mSharePreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mSharePreferences =
            getSharedPreferences(Constants.WEATHER_RESPONSE_DATA, Context.MODE_PRIVATE)

        setUpUI()
        if (!isLocationEnabled()) {
            println("not LocationEnabled++++++++++++++++++++++++++++++++++++++++++++\n")
            Toast.makeText(
                this,
                "your location provider is turned off Please turn it on",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        else {

            Dexter.withActivity(this).withPermissions(
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {
                        println(" all permission granted ++++++++++++++++++++++++++++++++++++++++++++\n")
                        requestLocationDate()
                    }
                    if (report.isAnyPermissionPermanentlyDenied) {
                        Toast.makeText(
                            this@MainActivity,
                            "you have denied location permission . please allow  ",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    showRationalDialogForPermissions()
                }
            }).onSameThread().check()

        }
    }


    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(
                    LocationManager.NETWORK_PROVIDER
                )
    }

    private fun getLocationWeatherDetails(latitude: Double, longitude: Double) {
        if (Constants.isNetworkAvailable(this)) {
            val retrofit: Retrofit =
                Retrofit.Builder().baseUrl(
                    Constants.BASE_URL
                ).addConverterFactory(GsonConverterFactory.create())
                    .build();
            val service: WeatherService = retrofit.create(WeatherService::class.java)

            val listCall =
                service.getWeather(latitude, longitude, Constants.METRIC_UNIT, Constants.APP_ID)
            showCustomProgressDialog()
            listCall.enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        hideProgressDialog()
                        val weatherList: WeatherResponse? = response.body()

                        val weatherResponseJsonStrong = Gson().toJson(weatherList)
                        val editor = mSharePreferences?.edit()
                        editor?.putString(
                            Constants.WEATHER_RESPONSE_DATA,
                            weatherResponseJsonStrong
                        )
                        editor?.apply()

                        setUpUI()//setup ui

                    } else {
                        val rc = response.code()
                        when (rc) {
                            400 -> {
                                Log.e("error 400 ", "Bad connection ")
                            }
                            404 -> {
                                Log.e("error 404 ", "not found ")
                            }
                            else ->
                                Log.e("error ", "Generic error  ")
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    hideProgressDialog()
                    Log.e("Error", t!!.message.toString())

                }

            })

        } else {
            Toast.makeText(
                this,
                "you have not connected to internet ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this).setMessage("it Look like you have turned off permission ")
            .setPositiveButton("Go to settings") { _, _ ->
                try {

                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    var uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }.setNegativeButton("cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationDate() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )

    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var mLastLocation: Location? = p0.lastLocation
            var latitude = mLastLocation?.latitude
            var longitude = mLastLocation?.longitude
            getLocationWeatherDetails(latitude!!, longitude!!)
        }

    }

    private fun hideProgressDialog() {
        mProgressDialog?.dismiss()

    }


    private fun showCustomProgressDialog() {
        mProgressDialog = Dialog(this)
        mProgressDialog!!.setContentView(R.layout.dialog_custom_progress)
        mProgressDialog!!.show()

    }

    private fun setUpUI() {

        var iv_main: ImageView = findViewById(R.id.iv_main)


        val tv_main: TextView = findViewById(R.id.tv_main)
        var tv_MainDescription: TextView = findViewById(R.id.tv_main_description)
        var tv_temp: TextView = findViewById(R.id.tv_temp)
        var tv_sunrise_time: TextView = findViewById(R.id.tv_sunrise_time)
        var tv_sunset_time: TextView = findViewById(R.id.tv_sunset_time)

        var tv_humidity: TextView = findViewById(R.id.tv_humidity)
        var tv_min: TextView = findViewById(R.id.tv_min)
        var tv_max: TextView = findViewById(R.id.tv_max)
        var tv_speed: TextView = findViewById(R.id.tv_speed)
        var tv_name: TextView = findViewById(R.id.tv_name)
        var tv_country: TextView = findViewById(R.id.tv_country)
        var weatherResponseJsonStrong =
            mSharePreferences?.getString(Constants.WEATHER_RESPONSE_DATA, "")
        if (!weatherResponseJsonStrong.isNullOrEmpty()) {
            val weatherList: WeatherResponse =
                Gson().fromJson(weatherResponseJsonStrong, WeatherResponse::class.java)
            for (i in weatherList.weather.indices) {
                Log.i("weathertest", weatherList.toString())

                tv_main.text = weatherList.weather[i].main
                tv_MainDescription.text = weatherList.weather[i].description
                tv_temp.text =
                    weatherList.main.temp.toString() + getUnit(application.resources.configuration.locales.toString())

                tv_sunrise_time.text = unixTime(weatherList.sys.sunrise)
                tv_sunset_time.text = unixTime(weatherList.sys.sunset)
                tv_humidity.text = weatherList.main.humidity.toString() + "percent"
                tv_min.text = weatherList.main.temp_min.toString() + "min"
                tv_max.text = weatherList.main.temp_max.toString() + "max"
                tv_speed.text = weatherList.wind.speed.toString()
                tv_name.text = weatherList.name
                tv_country.text = weatherList.sys.country


                when (weatherList.weather[i].icon) {
                    "01d" -> iv_main.setImageResource(R.drawable.sunny)
                    "02d" -> iv_main.setImageResource(R.drawable.cloud)
                    "03d" -> iv_main.setImageResource(R.drawable.cloud)
                    "04d" -> iv_main.setImageResource(R.drawable.cloud)
                    "04n" -> iv_main.setImageResource(R.drawable.cloud)
                    "10d" -> iv_main.setImageResource(R.drawable.rain)
                    "11d" -> iv_main.setImageResource(R.drawable.storm)
                    "13d" -> iv_main.setImageResource(R.drawable.snowflake)
                    "01n" -> iv_main.setImageResource(R.drawable.cloud)
                    "02n" -> iv_main.setImageResource(R.drawable.cloud)
                    "03n" -> iv_main.setImageResource(R.drawable.cloud)
                    "10n" -> iv_main.setImageResource(R.drawable.cloud)
                    "11n" -> iv_main.setImageResource(R.drawable.rain)
                    "13n" -> iv_main.setImageResource(R.drawable.snowflake)
                }
            }
        }

    }

    private fun getUnit(value: String): String? {
        var value = "oC"
        if ("US" == value || "LR" == value || "MM" == value) {
            value = "0F"
        }
        return value
    }

    private fun unixTime(timex: Long): String {
        val date = Date(timex * 1000L)
        val sdf = SimpleDateFormat("HH:mm")
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                requestLocationDate()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }
}