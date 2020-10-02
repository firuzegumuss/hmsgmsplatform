package com.example.util

interface OnReceiveMapCallback<M> {
    fun onReceiveMapView(mapView: M)
    fun onReceiveMap(map: M)
}