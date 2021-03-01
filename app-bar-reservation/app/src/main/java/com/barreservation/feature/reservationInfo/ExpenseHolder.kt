package com.barreservation.feature.reservationInfo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.barreservation.R
import com.barreservation.network.models.Expense
import kotlinx.android.synthetic.main.expense_list_item_layout.view.*

class ExpenseHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun onBind(item: Expense) =
        itemView.run {
            expenseName.text = item.name
            expenseAmountAndPrice.text =
                this.context.getString(R.string.expense_amount_and_price, item.amount, item.priceEur)
        }
}