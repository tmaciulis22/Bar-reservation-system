package com.barreservation.feature.reserve

import androidx.lifecycle.ViewModel
import com.barreservation.MockedBEandDatabase
import com.barreservation.network.models.Reservation

class ReserveViewModel : ViewModel() {

    var numberOfPeople: Int? = null
    var hour: Int? = null
    var minute: Int? = null
    var day: Int? = null
    var month: Int? = null
    var year: Int? = null

    fun buildReservation(barId: String, barName: String, userId:String, reservationId: String? = null): Reservation? {
        return Reservation(
            id = reservationId ?: MockedBEandDatabase.getNewReservationId(), //TODO When connected to BE, change to reservationId ?: null
            userId = userId,
            barId = barId,
            barName = barName,
            numberOfPeople = numberOfPeople ?: return null,
            hour = hour ?: return null,
            minute = minute ?: return null,
            day = day ?: return null,
            month = month ?: return null,
            year = year ?: return null
        )
    }

    //TODO Change to call to BE
    fun addReservation(reservation: Reservation) = MockedBEandDatabase.addReservation(reservation)

    //TODO Change to call to BE
    fun editReservation(reservation: Reservation) = MockedBEandDatabase.editReservation(reservation)

    //TODO Change to call to BE
    fun cancelReservation(reservation: Reservation) = MockedBEandDatabase.cancelReservation(reservation)
}