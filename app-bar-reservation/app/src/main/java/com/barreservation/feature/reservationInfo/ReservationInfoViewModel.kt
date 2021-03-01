package com.barreservation.feature.reservationInfo

import androidx.lifecycle.ViewModel
import com.barreservation.MockedBEandDatabase
import com.barreservation.network.models.Expense
import kotlin.math.roundToInt

class ReservationInfoViewModel : ViewModel() {

    var wasRatedPreviously: Boolean = false

    fun fetchOldRating(userId: String, barId: String): Int {
        val oldRating = MockedBEandDatabase.fetchOldRating(userId, barId)?.rating?.roundToInt()

        oldRating?.let {
            wasRatedPreviously = true
            return it
        }
        return 0
    }

    fun setRating(barId: String, userId: String, rating: Double) = MockedBEandDatabase.setRating(barId, userId, rating)

    fun calculateTotalAmount(expenses: List<Expense>) =
        expenses.fold(0.0) { acc, expense ->
            acc + expense.priceEur * expense.amount
        }
}