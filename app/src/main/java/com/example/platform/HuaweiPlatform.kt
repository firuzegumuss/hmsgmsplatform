package com.example.platform

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.example.model.Place
import com.example.util.OnReceiveMapCallback
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MarkerOptions

class HuaweiPlatform(private val context: Context) : IHuaweiPlatform, OnMapReadyCallback {
    private lateinit var onReceiveMapCallback: OnReceiveMapCallback<Any>
    private lateinit var huaweiMap: HuaweiMap
    override fun setMap(mapViewBundle: Bundle?, activity: Activity, callback: OnReceiveMapCallback<Any>) {
        onReceiveMapCallback = callback
        var mapView = MapView(activity.baseContext)
        mapView.apply {
            onCreate(mapViewBundle)
            getMapAsync(this@HuaweiPlatform) }
        onReceiveMapCallback.onReceiveMapView(mapView)
    }

    override fun addMarkers(coordinates: MutableList<Place>){
        for (place in coordinates) {
            val coordinate = LatLng(place.coordinateX, place.coordinateY)
            huaweiMap.addMarker(MarkerOptions().position(coordinate).title(place.title))
        }
        if (coordinates.isNotEmpty()) {
            val firstCoordinate = LatLng(coordinates[0].coordinateX, coordinates[0].coordinateY)
            huaweiMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstCoordinate, 13f))
        }

    }

    override fun onMapReady(huaweiMap: HuaweiMap) {
        this.huaweiMap = huaweiMap
        onReceiveMapCallback.onReceiveMap(huaweiMap)
    }

}