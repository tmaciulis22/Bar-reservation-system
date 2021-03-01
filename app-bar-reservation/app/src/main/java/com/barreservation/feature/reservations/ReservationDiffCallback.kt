package com.barreservation.feature.reservations

import androidx.recyclerview.widget.DiffUtil
import com.barreservation.network.models.Reservation

class ReservationDiffCallback : DiffUtil.ItemCallback<Reservation>() {
    override fun areItemsTheSame(
        oldItem: Reservation,
        newItem: Reservation
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Reservation,
        newItem: Reservation
    ): Boolean {
        return oldItem == newItem
    }
}