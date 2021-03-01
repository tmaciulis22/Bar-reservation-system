package com.barreservation.di

import com.barreservation.feature.profile.ProfileViewModel
import com.barreservation.feature.bars.BarsViewModel
import com.barreservation.feature.reservationInfo.ReservationInfoViewModel
import com.barreservation.feature.reservations.ReservationsViewModel
import com.barreservation.feature.reserve.ReserveViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Koin {

    private val viewModelModule = module(override = true) {
        viewModel { BarsViewModel() }
        viewModel { ReservationsViewModel() }
        viewModel { ProfileViewModel() }
        viewModel { ReserveViewModel() }
        viewModel { ReservationInfoViewModel() }
        viewModel { ProfileViewModel() }
    }

    val modules = listOf(viewModelModule)
}