package com.barreservation.widget

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.barreservation.R
import com.barreservation.feature.reserve.ReserveViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class TimePickerFragment(private val editText: EditText) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private val reserveViewModel: ReserveViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        reserveViewModel.apply {
            hour = hourOfDay
            this.minute = minute
        }
        editText.setText(getString(R.string.time_edit_text, hourOfDay, minute))
    }
}