package com.barreservation.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkingTime(
    val mon: Day,
    val tue: Day,
    val wed: Day,
    val thu: Day,
    val fri: Day,
    val sat: Day,
    val sun: Day
) : Parcelable

@Parcelize
data class Day(
    val opens: String,
    val closes: String
) : Parcelable