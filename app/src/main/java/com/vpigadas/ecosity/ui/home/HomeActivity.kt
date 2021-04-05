package com.vpigadas.ecosity.ui.home

import android.content.Intent
import android.graphics.Color
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vpigadas.ecosity.R
import com.vpigadas.ecosity.abstraction.AbstractActivity
import com.vpigadas.ecosity.ui.wizard.AddViewTreeActivity

class HomeActivity : AbstractActivity(R.layout.activity_home), OnMapReadyCallback {

    var googleMap: GoogleMap? = null

    private val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun initLayout() {
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            startActivity(Intent(this, AddViewTreeActivity::class.java))
        }
        viewModel.setup()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun startOperations() {

        viewModel.observerArea(this, Observer {
            it.forEach { area ->
                googleMap?.addPolygon(
                    PolygonOptions()
                        .clickable(true)
                        .fillColor(Color.BLUE)
                        .addAll(area.getLocations())
                )
            }
        })
    }

    override fun stopOperations() {
        viewModel.release(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
    }

}