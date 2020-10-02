package com.example.platform

import android.app.Activity
import android.os.Bundle
import com.example.model.Place
import com.example.util.OnReceiveMapCallback

class Platform(var platform: IPlatform) {
    fun setMap(mapViewBundle: Bundle?, activity: Activity, callback: OnReceiveMapCallback<Any>) = platform.setMap(mapViewBundle, activity, callback)
    fun addMarkers(coordinates: MutableList<Place>) = platform.addMarkers(coordinates)

}