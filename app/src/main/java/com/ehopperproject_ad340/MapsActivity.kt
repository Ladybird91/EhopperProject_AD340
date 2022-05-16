package com.ehopperproject_ad340

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationPermissionGranted = false
    private val defaultLocationSeattle = LatLng(47.608013, -122.335167)
    private val userLocation = Location(LocationManager.GPS_PROVIDER)
    private val zoomLevel = 12.0f
    private var lastKnownLocation: Location? = null
    private val permissionRequest = 1
    var cameraList = ArrayList<CameraDetails>()
    var coordinatesList = ArrayList<LatLng>()
    private var tag = "<MapActivity>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val bundle = intent.extras
        cameraList = bundle?.getParcelableArrayList<CameraDetails>("cameras") as ArrayList<CameraDetails>
        coordinatesList = bundle.getParcelableArrayList<LatLng>("coordinates") as ArrayList<LatLng>

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLocationPermission()
        getDeviceLocation()
        setMarkers()
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.e(tag, "Task successful")
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            Log.e(tag, "Setting user location")
                            mMap.addMarker(MarkerOptions().position(LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude)).icon(
                                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("Current Location"))
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                LatLng(lastKnownLocation!!.latitude,
                                    lastKnownLocation!!.longitude), zoomLevel))
                        }
                    } else {
                        Log.e(tag, "Current location is null. Using defaults.")
                        Log.e(tag, "Exception: %s", task.exception)
                        mMap.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(defaultLocationSeattle, zoomLevel))
                        mMap.uiSettings.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
            Log.e(tag, "Permission granted")
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                permissionRequest)
            Log.e(tag, "Not granted")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        locationPermissionGranted = false
        when (requestCode) {
            permissionRequest -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun setMarkers() {
        for (i in 0 until coordinatesList.size) {
            mMap.addMarker(MarkerOptions().position(coordinatesList[i]).title(cameraList[i].Description))
        }
    }
}