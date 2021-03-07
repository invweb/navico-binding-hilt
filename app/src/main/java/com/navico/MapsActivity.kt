package com.navico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.navico.data.Item

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val item = intent.extras!!.getParcelable<Item>(Constants.ITEM)
        val itemLocation = LatLng(item!!.location.lat, item.location.lng)
        mMap.addMarker(MarkerOptions().position(itemLocation).title(item.name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(itemLocation))
        val zoomLevel: Float = 14f
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(itemLocation,zoomLevel));
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
