package com.barreservation.feature.reservations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barreservation.MockedBEandDatabase
import com.barreservation.network.models.Reservation

class ReservationsViewModel : ViewModel() {

    val reservations = MutableLiveData<List<Reservation>>()

    //TODO Change to call to BE
    fun fetchReservationsList() = reservations.postValue(MockedBEandDatabase.fetchReservations("1")) //TODO change hardcoded 1
}