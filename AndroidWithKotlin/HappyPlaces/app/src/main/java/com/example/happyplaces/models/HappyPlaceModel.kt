package com.example.happyplaces.models

import android.os.Parcel
import android.os.Parcelable

data class HappyPlaceModel(
    val id: Int,
    val title: String?,
    val image: String?,
    var description: String?,
    var date: String?,
    var location: String?,
    var latitude: Double,
    var longitude: Double,


    ):Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun toString(): String {
        return "HappyPlaceModel(id=$id, title='$title', image='$image', description='$description', date='$date', location='$location', latitude=$latitude, longitude=$longitude)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeString(date)
        parcel.writeString(location)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HappyPlaceModel> {
        override fun createFromParcel(parcel: Parcel): HappyPlaceModel {
            return HappyPlaceModel(parcel)
        }

        override fun newArray(size: Int): Array<HappyPlaceModel?> {
            return arrayOfNulls(size)
        }
    }
}