package com.example.happyplaces.models

data class HappyPlaceModel(
    val id: Int,
    val title: String,
    val image: String,
    var description: String,
    var date: String,
    var location: String,
    var latitude: Double,
    var longitude: Double,

    )