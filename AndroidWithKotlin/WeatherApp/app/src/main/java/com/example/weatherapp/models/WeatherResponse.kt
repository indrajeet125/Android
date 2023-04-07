package com.example.weatherapp.models



data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt:Int,
    val sys: Sys,
    val id: Int,
    var name: String,
    var code: Int
) : java.io.Serializable