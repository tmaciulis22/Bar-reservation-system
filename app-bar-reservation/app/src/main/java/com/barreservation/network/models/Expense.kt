package com.barreservation.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Expense(
    val id: String,
    val reservationId: String,
    val name: String,
    val priceEur: Double,
    val amount: Int
) : Parcelable