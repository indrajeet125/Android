package com.example.weatherapp.models

import android.app.Notification.MessagingStyle.Message

data class Sys(
    val type: Int,
    val message: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int
) : java.io.Serializable