package com.barreservation.feature.bars

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barreservation.MockedBEandDatabase
import com.barreservation.network.models.Bar
import com.google.android.gms.maps.model.Marker

class BarsViewModel : ViewModel() {

    private val markerBarMap = mutableMapOf<Marker, String>()

    val barsInfolist = MutableLiveData<List<Triple<String, Double, Double>>>()

    fun addMarkerAndBarId(marker: Marker, barId: String) { markerBarMap[marker] = barId }

    fun findBarByMarker(marker: Marker): Bar? {
        val barId = markerBarMap[marker]
        barId?.let { return MockedBEandDatabase.fetchBarInfoById(it) } ?: return null
    }

    //TODO change to call to BE later on
    fun fetchBarsInfo() = barsInfolist.postValue(MockedBEandDatabase.fetchBarsInfo())
}