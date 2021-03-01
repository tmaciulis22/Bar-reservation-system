package com.barreservation.feature.reserve

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.barreservation.R
import com.barreservation.extension.addAnimationEndListener
import com.barreservation.extension.setSupportActionBarWithNoTitle
import com.barreservation.network.models.Reservation
import com.barreservation.widget.DatePickerFragment
import com.barreservation.widget.TimePickerFragment
import kotlinx.android.synthetic.main.activity_bar_info.toolbar
import kotlinx.android.synthetic.main.activity_reserve.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReserveActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    private val barId by lazy {
        intent.getStringExtra(EXTRA_BAR_ID)
    }

    private val barName by lazy {
        intent.getStringExtra(EXTRA_BAR_NAME)
    }

    private val oldReservation: Reservation? by lazy {
        intent.getParcelableExtra<Reservation>(EXTRA_RESERVATION)
    }

    private val reserveViewModel: ReserveViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)

        initView()
        addClickListeners()
        initViewModel()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        oldReservation?.let {
            reserveViewModel.numberOfPeople = it.numberOfPeople
            return
        }
        reserveViewModel.numberOfPeople = 1
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position < 4)
            reserveViewModel.numberOfPeople = parent?.getItemAtPosition(position).toString().toInt()
        else
            reserveViewModel.numberOfPeople = 5
    }

    private fun initView() {
        setSupportActionBarWithNoTitle(toolbar)

        barHeader.text = barName ?: oldReservation?.barName

        ArrayAdapter.createFromResource(
            this,
            R.array.people_numbers,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            peopleNumberSpinner.apply {
                adapter = it
                onItemSelectedListener = this@ReserveActivity
                oldReservation?.let { reservation -> setSelection(reservation.numberOfPeople - 1) }
            }
        }

        timePicker.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener { TimePickerFragment(this).show(supportFragmentManager, "timePicker") }
            oldReservation?.let { setText(getString(R.string.time_edit_text, it.hour, it.minute)) }
        }
        datePicker.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener { DatePickerFragment(this).show(supportFragmentManager, "datePicker") }
            oldReservation?.let { setText(getString(R.string.date_edit_text, it.day, it.month, it.year)) }
        }

        oldReservation?.let {
            status.visibility = View.VISIBLE
            status.text = getString(R.string.accepted_status, it.isAccepted.toString())
            sendButton.text = getString(R.string.edit_reservation)
        }
    }

    private fun addClickListeners() {
        sendButton.setOnClickListener {
            reserveViewModel.apply {
                var reservation: Reservation? = null

                if (oldReservation != null)
                    oldReservation?.let {
                        reservation = buildReservation(it.barId, it.barName, "1", it.id)
                    }
                else if (barName != null && barId != null)
                    reservation = buildReservation(barId, barName, "1")

                reservation?.let {
                    sent_animation.apply {
                        if (oldReservation != null)
                            editReservation(it)
                        else
                            addReservation(it)

                        visibility = View.VISIBLE
                        playAnimation()
                        addAnimationEndListener {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    }
                }
            }
        }

        cancelButton.setOnClickListener {
            oldReservation?.let {
                reserveViewModel.cancelReservation(it)
                setResult(Activity.RESULT_OK)
            }
            finish()
        }
    }

    private fun initViewModel() {
        reserveViewModel.apply {
            oldReservation?.let {
                numberOfPeople = it.numberOfPeople
                hour = it.hour
                minute = it.minute
                day = it.day
                month = it.month
                year = it.year
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_BAR_ID = "ReserveActivity.EXTRA_BAR_ID"
        private const val EXTRA_BAR_NAME = "ReserveActivity.EXTRA_BAR_NAME"
        private const val EXTRA_RESERVATION = "ReserveActivity.EXTRA_RESERVATION"

        fun newIntentForAdding(
            context: Context,
            barId: String,
            barName: String
        ): Intent = Intent(context, ReserveActivity::class.java)
            .putExtra(EXTRA_BAR_ID, barId)
            .putExtra(EXTRA_BAR_NAME, barName)

        fun newIntentForEditing(
            context: Context,
            reservation: Reservation
        ): Intent = Intent(context, ReserveActivity::class.java)
            .putExtra(EXTRA_RESERVATION, reservation)
    }
}