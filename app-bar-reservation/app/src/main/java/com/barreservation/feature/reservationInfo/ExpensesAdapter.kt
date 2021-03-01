package com.barreservation.feature.reservationInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.barreservation.R
import com.barreservation.network.models.Expense

class ExpensesAdapter(private val layoutInflater: LayoutInflater)
    : ListAdapter<Expense, ExpenseHolder>(ExpensesDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHolder =
        ExpenseHolder(layoutInflater.inflate(R.layout.expense_list_item_layout, parent, false))

    override fun onBindViewHolder(holder: ExpenseHolder, position: Int) =
        holder.onBind(getItem(position))
}