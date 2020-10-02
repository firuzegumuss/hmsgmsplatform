package com.example.platform

import android.app.Activity
import android.os.Bundle
import com.example.model.Place
import com.example.util.OnReceiveMapCallback


interface IGooglePlatform : IPlatform {
    // interface for the methods only Huawei has

    // for the commons override
    override fun setMap(mapViewBundle: Bundle?, activity: Activity, callback: OnReceiveMapCallback<Any>)
    override fun addMarkers(coordinates: MutableList<Place>)

}