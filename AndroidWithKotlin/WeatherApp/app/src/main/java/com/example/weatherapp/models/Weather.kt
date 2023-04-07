package com.example.weatherapp.models

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    var icon: String
) : java.io.Serializable