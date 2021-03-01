package com.barreservation.network.models

data class Rating(
    val id: String,
    val userId: String,
    val barId: String,
    var rating: Double
)