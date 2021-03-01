package com.barreservation.feature.reservationInfo

import androidx.recyclerview.widget.DiffUtil
import com.barreservation.network.models.Expense

class ExpensesDiffCallback : DiffUtil.ItemCallback<Expense>() {
    override fun areItemsTheSame(
        oldItem: Expense,
        newItem: Expense
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Expense,
        newItem: Expense
    ): Boolean {
        return oldItem == newItem
    }
}