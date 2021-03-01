package com.barreservation.feature.reservations

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.barreservation.R
import com.barreservation.network.models.Reservation
import kotlinx.android.synthetic.main.reservation_list_item_layout.view.*

class ReservationHolder(itemView: View, private val clickedReservationPosition: MutableLiveData<Int>) :
    RecyclerView.ViewHolder(itemView) {

    fun onBind(item: Reservation) =
        itemView.run {
            barNameLabel.text = item.barName
            if (item.isCompleted)
                status.text = this.context.getString(R.string.completed_status)
            else
                status.text = this.context.getString(R.string.accepted_status, item.isAccepted.toString())
            date.text = this.context.getString(R.string.date_edit_text, item.day, item.month, item.year)
            time.text = this.context.getString(R.string.time_edit_text, item.hour, item.minute)
            
            setOnClickListener {
                clickedReservationPosition.value = adapterPosition
            }
        }
}