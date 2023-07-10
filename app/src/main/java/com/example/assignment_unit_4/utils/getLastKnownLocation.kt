package com.example.assignment_unit_4.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@SuppressLint("MissingPermission")
private suspend fun getLastKnownLocation(context: Context): Location? {
  val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
  val locationTask = fusedLocationClient.lastLocation

  return suspendCoroutine { continuation ->
    locationTask.addOnSuccessListener { location ->
      continuation.resume(location)
    }
    locationTask.addOnFailureListener { exception ->
      Log.e("Location", "Error getting location", exception)
      continuation.resume(null)
    }
  }
}