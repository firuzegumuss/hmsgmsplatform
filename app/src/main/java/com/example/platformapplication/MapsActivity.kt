package com.example.platformapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.model.Place
import com.example.platform.GooglePlatform
import com.example.platform.HuaweiPlatform
import com.example.platform.Platform
import com.example.util.OnReceiveMapCallback
import com.example.util.PlatformType
import com.example.util.getPlatformType
import kotlinx.android.synthetic.main.activity_maps.*

private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

class MapsActivity : AppCompatActivity(){

    private lateinit var map: Any
    private var platform: Platform? = null

    var coordinates = mutableListOf<Place>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle =
                savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }

        setCoordinates()
        initPlatform()
        platform?.setMap(mapViewBundle, this, object: OnReceiveMapCallback<Any>{
            override fun onReceiveMap(anyMap: Any) {
                map = anyMap
                platform?.addMarkers(coordinates)
            }

            override fun onReceiveMapView(mapView: Any) {
                mapViewContainer.addView(mapView as View?)
            }
        })
    }

    private fun setCoordinates(){
        coordinates.add(Place(41.028985, 29.117591, "Huawei R&D"))
        coordinates.add(Place(41.025509, 29.127291, "Ikea"))
        coordinates.add(Place( 41.026774, 29.126867, "Buyaka"))
        coordinates.add(Place( 41.025099, 29.106688, "Canpark"))
    }

    private fun initPlatform() {
        var platformType = getPlatformType(application)
        platform = when (platformType) {
            PlatformType.GMS -> {
                Platform(GooglePlatform(application.applicationContext))
            }
            PlatformType.HMS -> {
                Platform(HuaweiPlatform(application.applicationContext))
            }
            else -> null
        }
    }
}