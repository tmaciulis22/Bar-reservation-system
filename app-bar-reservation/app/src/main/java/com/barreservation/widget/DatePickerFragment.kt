package com.barreservation.widget

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.barreservation.R
import com.barreservation.feature.reserve.ReserveViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class DatePickerFragment(private val editText: EditText) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val reserveViewModel: ReserveViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        reserveViewModel.apply {
            this.year = year
            this.month = month + 1
            this.day = day
        }
        editText.setText(getString(R.string.date_edit_text, day, month + 1, year))
    }
}