package com.ehopperproject_ad340

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import kotlinx.parcelize.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class LiveCameraInfo(
    val Features: List<Features>
) {
    companion object {
        private var tag = "LiveCameraInfo"
        @SuppressLint("MissingPermission")
        fun getLiveCameraInfo(context: Context, classToStart : Class<*>){
            try {
                val connectivityManager =
                    context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
                connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        Log.e(tag, "Connected to network.")
                        val call: Call<LiveCameraInfo> = CamApi.retrofitService.getProperties()
                        call.enqueue(object : Callback<LiveCameraInfo> {
                            override
                            fun onResponse(call: Call<LiveCameraInfo>, response: Response<LiveCameraInfo>) {
                                Log.e(tag, "API call started.")
                                val data = response.body()?.Features
                                val allCameras = ArrayList<CameraDetails>()
                                val allCoordinates = ArrayList<LatLng>()
                                for (i in data!!.indices) {
                                    val item = data[i].Cameras
                                    allCameras.addAll(item)
                                    val coordinates = data[i].PointCoordinate
                                    allCoordinates.add(LatLng(coordinates[0], coordinates[1]))
                                }
                                val bundle = Bundle()
                                bundle.putParcelableArrayList("cameras", allCameras)
                                bundle.putParcelableArrayList("coordinates", allCoordinates)
                                val intent = Intent(context, classToStart)
                                intent.putExtras(bundle)
                                context.startActivity(intent)
                            }
                            override
                            fun onFailure(call: Call<LiveCameraInfo>, t: Throwable) {
                                Log.e(tag, "API call not started.")
                                Toast.makeText(context.applicationContext, R.string.apiError, Toast.LENGTH_LONG).show()
                            }
                        })
                    }
                    override fun onLost(network: Network) {
                        Log.e(tag, "No connection to network.")
                        Toast.makeText(context.applicationContext, R.string.connectError, Toast.LENGTH_LONG).show()
                        // Try to figure out out to implement correctly later

                        //Snackbar.make(view.findViewById(android.R.id.content), R.string.connectError, Snackbar.LENGTH_INDEFINITE).show()
                    }
                })
            } catch (e: Exception) {
                Log.e(tag, e.toString())
            }
        }
    }
}

data class Features(
    val Cameras: List<CameraDetails>,
    val PointCoordinate: List<Double>
)

@Parcelize
data class CameraDetails(
    val Id: String,
    val Description: String,
    val ImageUrl: String,
    val Type: String

): Parcelable {

    fun getFullImageUrl(): String {
        return if (Type == "sdot") {
            "https://www.seattle.gov/trafficcams/images/$ImageUrl"
        } else {
            "https://images.wsdot.wa.gov/nw/$ImageUrl"
        }
    }
}










