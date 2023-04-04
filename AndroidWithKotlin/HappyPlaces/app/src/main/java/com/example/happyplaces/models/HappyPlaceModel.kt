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


    ) {
    override fun toString(): String {
        return "HappyPlaceModel(id=$id, title='$title', image='$image', description='$description', date='$date', location='$location', latitude=$latitude, longitude=$longitude)"
    }
}