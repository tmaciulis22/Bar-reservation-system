package com.barreservation.feature.bars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.barreservation.R
import com.barreservation.network.models.Bar
import com.barreservation.feature.barInfo.BarInfoActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import kotlinx.android.synthetic.main.fragment_bars.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BarsFragment : Fragment(), OnMapReadyCallback {

    private val barsViewModel: BarsViewModel by viewModel()

    private val fragmentLayoutRes = R.layout.fragment_bars

    private lateinit var googleMap: GoogleMap
    private val googleMapView: MapView?
        get() = mapView ?: null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()

        return inflater.inflate(fragmentLayoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        googleMapView?.onCreate(savedInstanceState)
        googleMapView?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        map.apply {
            googleMap = this
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.dark_map_style))
            moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_VILNIUS_LAT_LNG, DEFAULT_VILNIUS_ZOOM))
            addMarkers()
            setOnMarkerClickListener {
                val bar = barsViewModel.findBarByMarker(it)

                bar?.run { startActivity(BarInfoActivity.newIntent(requireContext(), bar = this)) }

                true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        googleMapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        googleMapView?.onResume()
    }

    override fun onPause() {
        googleMapView?.onPause()
        super.onPause()
    }

    override fun onStop() {
        googleMapView?.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        googleMapView?.onDestroy()
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        googleMapView?.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        googleMapView?.onLowMemory()
        super.onLowMemory()
    }

    private fun initViewModel() = barsViewModel.fetchBarsInfo()

    private fun addMarkers() =
        barsViewModel.barsInfolist.value?.forEach {
            val marker = googleMap.addMarker(MarkerOptions().position(LatLng(it.second, it.third)))

            barsViewModel.addMarkerAndBarId(marker, it.first)
        }

    companion object {
        private val DEFAULT_VILNIUS_LAT_LNG = LatLng(54.6872, 25.2797)
        private const val DEFAULT_VILNIUS_ZOOM = 13f
    }
}