package com.barreservation.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bar(
    val id: String,
    val name: String,
    var averageRating: Double,
    val address: String,
    val phoneNumber: String,
    val workingTime: WorkingTime,
    val lat: Double,
    val lon: Double
) : Parcelable