package com.barreservation.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reservation(
    val id: String,
    val userId: String,
    val barId: String,
    val barName: String,
    val numberOfPeople: Int,
    val hour: Int,
    val minute: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val isAccepted: Boolean = false,
    val isCompleted: Boolean = false,
    val expenses: List<Expense> = emptyList()
) : Parcelable