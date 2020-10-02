package com.example.platform

import android.app.Activity
import android.os.Bundle
import com.example.model.Place
import com.example.util.OnReceiveMapCallback

interface IPlatform{
    // interface for the common methods both Google and Huawei have

    fun setMap(mapViewBundle: Bundle?, activity: Activity, callback: OnReceiveMapCallback<Any>)
    fun addMarkers(coordinates: MutableList<Place>)
}