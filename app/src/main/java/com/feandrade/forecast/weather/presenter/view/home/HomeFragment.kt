package com.feandrade.forecast.weather.presenter.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.feandrade.forecast.weather.BuildConfig
import com.feandrade.forecast.weather.R
import com.feandrade.forecast.weather.core.Status
import com.feandrade.forecast.weather.databinding.FragmentHomeOneBinding
import com.feandrade.forecast.weather.domain.di.home.HomeComponent
import com.feandrade.forecast.weather.presenter.viewmodel.HomeViewModel
import com.feandrade.forecast.weather.utils.hideKeyboard
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.ln

class HomeFragment : Fragment(), OnMapReadyCallback {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var binding: FragmentHomeOneBinding
    private lateinit var editTextValue: String
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeComponent.inject()

        val mapFragment = childFragmentManager.findFragmentById(R.id.frag_map) as SupportMapFragment
        mapFragment.getMapAsync{onMapReady(it)}
        initView()
        observerVMEvents()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    private fun setMarker(latLng : LatLng) {
//        val latLng = LatLng(lat, lng)
        map.clear()
        map.addMarker(MarkerOptions().position(latLng))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun initView() {
        getEdtValueToCache()
        callSendResponse()
    }

    private fun callSendResponse() {
        binding.imgArrow.setOnClickListener {
            getWeather()
        }
    }

    private fun getEdtValueToCache() {
        binding.cityTextEDT.addTextChangedListener { edt ->
            if (edt.toString().isNotEmpty())
                editTextValue = edt.toString()
            homeViewModel.saveUserCity(editTextValue)
        }
    }

    private fun observerVMEvents() {
        homeViewModel.response.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        homeViewModel.getLatLng(response.coord.lat, response.coord.lon)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Error: ${it.error}", Toast.LENGTH_SHORT)
                        .show()
                }

                Status.LOADING -> {

                }
            }
        }
        homeViewModel._latLng.observe(viewLifecycleOwner) {
            setMarker(it)

        }
    }

    private fun getCity(): String = homeViewModel.getSaveCity()

    private fun getWeather() =
        homeViewModel.getAllWeatherData(getCity(), BuildConfig.API_KEY)



}