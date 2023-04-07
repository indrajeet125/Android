package com.example.weatherapp.models

data class Main(
    val temp:Double,
    val pressure:Double,
    val humidity:Int,
    val temp_min:Double,
    val temp_max:Double,
    val sea_level:Double,
    val gmd_level:Double

):java.io.Serializable
