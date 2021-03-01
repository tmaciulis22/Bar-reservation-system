package com.barreservation.feature.reservationInfo

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.barreservation.R
import com.barreservation.extension.setSupportActionBarWithNoTitle
import com.barreservation.network.models.Reservation
import kotlinx.android.synthetic.main.activity_reservation_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReservationInfoActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val reservation: Reservation by lazy {
        intent.getParcelableExtra<Reservation>(EXTRA_RESERVATION)
    }

    private val expensesAdapter by lazy {
        ExpensesAdapter(LayoutInflater.from(this))
    }

    private val reservationInfoViewModel: ReservationInfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_info)

        initView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (reservationInfoViewModel.wasRatedPreviously) {
            reservationInfoViewModel.wasRatedPreviously = false
            return
        }
        reservationInfoViewModel.setRating(reservation.barId, reservation.userId, parent?.getItemAtPosition(position).toString().toDouble())
        Toast.makeText(this, getString(R.string.rating_saved_message), Toast.LENGTH_SHORT).show()
    }


    private fun initView() {
        setSupportActionBarWithNoTitle(toolbar)

        reservation.apply {
            barNameLabel.text = barName
            date.text = getString(R.string.date_edit_text, day, month, year)
            time.text = getString(R.string.time_edit_text, hour, minute)
            numberOfPeopleLabel.text = resources.getQuantityString(R.plurals.number_of_persons, numberOfPeople, numberOfPeople)
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.ratings,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            peopleNumberSpinner.apply {
                adapter = it
                onItemSelectedListener = this@ReservationInfoActivity
                setSelection(reservationInfoViewModel.fetchOldRating(reservation.userId, reservation.barId) - 1)
            }
        }

        totalSpentAmount.text = getString(
            R.string.spent_amount,
            reservationInfoViewModel.calculateTotalAmount(reservation.expenses)
        )

        expensesList.adapter = expensesAdapter

        val divider = DividerItemDecoration(expensesList.context, LinearLayoutManager.VERTICAL)
        divider.setDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.lynx_white)))

        expensesList.addItemDecoration(divider)

        expensesAdapter.submitList(reservation.expenses)
    }

    companion object {
        private const val EXTRA_RESERVATION = "ReservationInfoActivity.EXTRA_RESERVATION"

        fun newIntent(
            context: Context,
            reservation: Reservation
        ): Intent =
            Intent(context, ReservationInfoActivity::class.java)
                .putExtra(EXTRA_RESERVATION, reservation)
    }
}