package com.example.util

import android.app.Application
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.ConnectionResult
import com.huawei.hms.api.HuaweiApiAvailability

fun getPlatformType(application: Application): PlatformType {
    return when {
        (isGmsAvailable(application) && isHmsAvailable(application)) || isGmsAvailable(application)-> PlatformType.GMS
        isHmsAvailable(application) && Build.MANUFACTURER == "HUAWEI" -> PlatformType.HMS // HMS Map Kit SDK supports Huawei Devices
        else -> return PlatformType.OTHER
    }
}

fun isHmsAvailable(context: Context?): Boolean {
        var isAvailable = false
        if (null != context) {
            val result =
                HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context)
            when(result)
            {
                ConnectionResult.SUCCESS ->  isAvailable = true
                ConnectionResult.SERVICE_DISABLED,  ConnectionResult.SERVICE_INVALID,
                ConnectionResult.SERVICE_MISSING -> isAvailable = false
                ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ->{
                    Toast.makeText(context, "Please update HMS Core", Toast.LENGTH_LONG).show()
                    isAvailable = false
                }
            }
        }
        return isAvailable
}

fun isGmsAvailable(context: Context?): Boolean {
        var isAvailable = false
        if (null != context) {
            when(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)){
                 ConnectionResult.SUCCESS ->  isAvailable = true
                 ConnectionResult.SERVICE_DISABLED,  ConnectionResult.SERVICE_INVALID,
                 ConnectionResult.SERVICE_MISSING -> isAvailable = false
                 ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ->{
                     Toast.makeText(context, "Please update Google Play Services", Toast.LENGTH_LONG).show()
                     isAvailable = false
                 }
            }
        }
        return isAvailable
}
