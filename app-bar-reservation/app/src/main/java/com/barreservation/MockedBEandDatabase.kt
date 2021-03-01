package com.barreservation

import com.barreservation.network.models.*

//TODO Delete this object when app will be connected to Back-End, and replace it with calls to BE
object MockedBEandDatabase {

    private val reservationList = mutableListOf(
        Reservation(
            id = "1",
            userId = "1",
            barId = "1",
            barName = "Bukowski",
            numberOfPeople = 3,
            hour = 20,
            minute = 20,
            year = 2019,
            month = 10,
            day = 25
        ),
        Reservation(
            id = "2",
            userId = "1",
            barId = "5",
            barName = "Devėti",
            numberOfPeople = 3,
            hour = 19,
            minute = 30,
            year = 2019,
            month = 10,
            day = 20,
            isAccepted = true
        ),
        Reservation(
            id = "3",
            userId = "1",
            barId = "2",
            barName = "Peronas",
            numberOfPeople = 4,
            hour = 19,
            minute = 10,
            year = 2019,
            month = 10,
            day = 4,
            isAccepted = true,
            isCompleted = true,
            expenses = listOf(
                Expense(
                    id = "1",
                    reservationId = "3",
                    name = "Newcastle beer",
                    amount = 4,
                    priceEur = 3.5
                ),
                Expense(
                    id = "2",
                    reservationId = "3",
                    name = "Heineken",
                    amount = 2,
                    priceEur = 2.0
                )
            )
        )
    )

    private val barsList = listOf(
        Bar(
            id = "1",
            address = "Visų šventųjų g. 7",
            name = "Bukowski",
            averageRating = 4.7,
            phoneNumber = "+37069484729",
            workingTime = WorkingTime(
                mon = Day(opens = "16:00", closes = "02:00"),
                tue = Day(opens = "16:00", closes = "02:00"),
                wed = Day(opens = "16:00", closes = "02:00"),
                thu = Day(opens = "16:00", closes = "02:00"),
                fri = Day(opens = "16:00", closes = "02:00"),
                sat = Day(opens = "16:00", closes = "02:00"),
                sun = Day(opens = "16:00", closes = "02:00")
            ),
            lat = 54.6750,
            lon = 25.2853
        ),
        Bar(
            id = "2",
            address = "Geležinkelio g. 6",
            name = "Peronas",
            averageRating = 5.0,
            phoneNumber = "+37069484729",
            workingTime = WorkingTime(
                mon = Day(opens = "16:00", closes = "02:00"),
                tue = Day(opens = "16:00", closes = "02:00"),
                wed = Day(opens = "16:00", closes = "02:00"),
                thu = Day(opens = "16:00", closes = "02:00"),
                fri = Day(opens = "16:00", closes = "02:00"),
                sat = Day(opens = "16:00", closes = "02:00"),
                sun = Day(opens = "16:00", closes = "02:00")
            ),
            lat = 54.6702301,
            lon = 25.2829591
        ),
        Bar(
            id = "3",
            address = "Vilniaus g. 39",
            name = "Plus Plus Plus",
            averageRating = 4.5,
            phoneNumber = "+37069484729",
            workingTime = WorkingTime(
                mon = Day(opens = "16:00", closes = "02:00"),
                tue = Day(opens = "16:00", closes = "02:00"),
                wed = Day(opens = "16:00", closes = "02:00"),
                thu = Day(opens = "16:00", closes = "02:00"),
                fri = Day(opens = "16:00", closes = "02:00"),
                sat = Day(opens = "16:00", closes = "02:00"),
                sun = Day(opens = "16:00", closes = "02:00")
            ),
            lat = 54.682284,
            lon = 25.279756
        ),
        Bar(
            id = "4",
            address = "Trakų g. 15",
            name = "Marsas",
            averageRating = 4.6,
            phoneNumber = "+37069484729",
            workingTime = WorkingTime(
                mon = Day(opens = "16:00", closes = "02:00"),
                tue = Day(opens = "16:00", closes = "02:00"),
                wed = Day(opens = "16:00", closes = "02:00"),
                thu = Day(opens = "16:00", closes = "02:00"),
                fri = Day(opens = "16:00", closes = "02:00"),
                sat = Day(opens = "16:00", closes = "02:00"),
                sun = Day(opens = "16:00", closes = "02:00")
            ),
            lat = 54.680296,
            lon = 25.281660
        ),
        Bar(
            id = "5",
            address = "Sodų g. 3",
            name = "Devėti",
            averageRating = 4.7,
            phoneNumber = "+37069484729",
            workingTime = WorkingTime(
                mon = Day(opens = "16:00", closes = "02:00"),
                tue = Day(opens = "16:00", closes = "02:00"),
                wed = Day(opens = "16:00", closes = "02:00"),
                thu = Day(opens = "16:00", closes = "02:00"),
                fri = Day(opens = "16:00", closes = "02:00"),
                sat = Day(opens = "16:00", closes = "02:00"),
                sun = Day(opens = "16:00", closes = "02:00")
            ),
            lat = 54.673967,
            lon = 25.284403
        ),
        Bar(
            id = "6",
            address = "Pilies g. 6",
            name = "Pilies pasažas",
            averageRating = 4.7,
            phoneNumber = "+37069484729",
            workingTime = WorkingTime(
                mon = Day(opens = "16:00", closes = "02:00"),
                tue = Day(opens = "16:00", closes = "02:00"),
                wed = Day(opens = "16:00", closes = "02:00"),
                thu = Day(opens = "16:00", closes = "02:00"),
                fri = Day(opens = "16:00", closes = "02:00"),
                sat = Day(opens = "16:00", closes = "02:00"),
                sun = Day(opens = "16:00", closes = "02:00")
            ),
            lat = 54.684178,
            lon = 25.290062
        )
    )

    private val ratingsList = mutableListOf(
        Rating(
            id = "1",
            userId = "1",
            barId = "2",
            rating = 5.0
        )
    )

    fun fetchBarsInfo() = barsList.map { Triple(it.id, it.lat, it.lon) }

    fun fetchBarInfoById(barId: String) = barsList.first { it.id == barId }

    fun fetchReservations(userId: String) = reservationList.filter { it.userId == userId }

    fun addReservation(reservation: Reservation) = reservationList.add(reservation)

    fun editReservation(reservation: Reservation) {
        val index = reservationList.indexOf(reservationList.first { it.id == reservation.id })
        reservationList[index] = reservation
    }

    fun cancelReservation(reservation: Reservation) = reservationList.remove(reservation)

    fun setRating(barId: String, userId: String, rating: Double) {
        val oldRating = ratingsList.find { it.barId == barId && it.userId == userId }

        oldRating?.let {
            val index = ratingsList.indexOf(it)
            it.rating = rating
            ratingsList.set(index, it)
        } ?: ratingsList.add(Rating(getNewRatingId(), userId, barId, rating))

        val barRatings = ratingsList.filter { it.barId ==  barId }
        val bar = barsList.find { it.id == barId }

        bar?.averageRating = barRatings.fold(0.0) { acc, rating -> acc + rating.rating } / barRatings.count()
    }

    fun fetchOldRating(userId: String, barId: String) = ratingsList.find {
        it.userId == userId && it.barId == barId
    }

    //TODO This to be handled on BE later on, when it gets new Reservation without ID
    fun getNewReservationId() = (reservationList.count() + 1).toString()

    //TODO This to be handled on BE later on, when it gets new Rating without ID
    private fun getNewRatingId() = (ratingsList.count() + 1).toString()
}