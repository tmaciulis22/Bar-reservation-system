package com.barreservation.feature.reservations

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.barreservation.R
import com.barreservation.feature.reservationInfo.ReservationInfoActivity
import com.barreservation.feature.reserve.ReserveActivity
import com.barreservation.network.models.Bar
import com.barreservation.network.models.Reservation
import kotlinx.android.synthetic.main.fragment_reservations.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReservationsFragment : Fragment() {

    private val reservationsViewModel: ReservationsViewModel by viewModel()

    private val reservationsAdapter by lazy {
        ReservationsAdapter(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        addObservers()
        reservationsViewModel.fetchReservationsList()
    }

    private fun initView() {
        reservationsList.adapter = reservationsAdapter

        val divider = DividerItemDecoration(reservationsList.context, LinearLayoutManager.VERTICAL)
        divider.setDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.lynx_white)))

        reservationsList.addItemDecoration(divider)
    }

    private fun addObservers() {
        reservationsViewModel.reservations.observe(this, Observer {
            reservationsAdapter.submitList(reservationsViewModel.reservations.value)
        })

        reservationsAdapter.clickedReservationPosition.observe(this, Observer {
            val reservation = reservationsAdapter.getReservation(it) as Reservation
            if (reservation.isCompleted)
                startActivity(ReservationInfoActivity.newIntent(requireContext(), reservation))
            else
                startActivityForResult(
                    ReserveActivity.newIntentForEditing(
                        requireContext(),
                        reservation
                    ), REQUEST_CODE_EDIT
                )
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_EDIT) {
            reservationsViewModel.fetchReservationsList()
            resetAdapterState()
        }
    }

    //Hacky but working way of redrawing reservation list on UI
    private fun resetAdapterState() {
        val adapter = reservationsList.adapter
        reservationsList.adapter = adapter
    }

    companion object {
        private const val REQUEST_CODE_EDIT = 101
    }
}