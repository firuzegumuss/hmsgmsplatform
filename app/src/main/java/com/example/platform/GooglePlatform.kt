package com.example.platform

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.example.model.Place
import com.example.util.OnReceiveMapCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.huawei.hms.maps.HuaweiMap

class GooglePlatform(private val context: Context) : IGooglePlatform, OnMapReadyCallback {

    private lateinit var onReceiveMapCallback: OnReceiveMapCallback<Any>
    private lateinit var googleMap: GoogleMap

    override fun setMap(mapViewBundle: Bundle?, activity: Activity, callback: OnReceiveMapCallback<Any>) {
        onReceiveMapCallback = callback
        var mapView =  MapView(activity.baseContext)
        mapView.apply {
            onCreate(mapViewBundle)
            getMapAsync(this@GooglePlatform) }
        onReceiveMapCallback.onReceiveMapView(mapView)
        mapView.onResume()
    }

    override fun addMarkers(coordinates: MutableList<Place>){
        for (place in coordinates) {
            val coordinate = LatLng(place.coordinateX, place.coordinateY)
            googleMap.addMarker(MarkerOptions().position(coordinate).title(place.title))
        }
        if (coordinates.isNotEmpty()) {
            val firstCoordinate = LatLng(coordinates[0].coordinateX, coordinates[0].coordinateY)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstCoordinate, 13f))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        onReceiveMapCallback.onReceiveMap(googleMap)
    }

}