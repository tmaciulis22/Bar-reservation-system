package com.barreservation.feature.reservations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import com.barreservation.R
import com.barreservation.network.models.Reservation

class ReservationsAdapter(private val layoutInflater: LayoutInflater)
    : ListAdapter<Reservation, ReservationHolder>(ReservationDiffCallback()) {

    val clickedReservationPosition = MutableLiveData<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationHolder =
        ReservationHolder(layoutInflater.inflate(R.layout.reservation_list_item_layout, parent, false), clickedReservationPosition)

    override fun onBindViewHolder(holder: ReservationHolder, position: Int) =
        holder.onBind(getItem(position))

    fun getReservation(position: Int) =
        if (position >= 0) {
            getItem(position)
        } else {
            null
        }
}