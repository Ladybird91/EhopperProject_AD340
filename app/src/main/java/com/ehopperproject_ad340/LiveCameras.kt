package com.ehopperproject_ad340


import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LiveCameras : AppCompatActivity() {



    var recyclerView: RecyclerView? = null
    var adapter = CameraAdapter()
    private var TAG = "LiveCameras"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_cameras)

        recyclerView = findViewById(R.id.recycler2)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter

        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        try {
            val connectivityManager =
                getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.registerDefaultNetworkCallback(object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    //Log.e(TAG, "Connected")
                    val call: Call<CallResponse> = CamApi.retrofitService.getProperties()
                    call.enqueue(object : Callback<CallResponse> {
                        override
                        fun onResponse(call: Call<CallResponse>, response: Response<CallResponse>) {
                            //Log.e(TAG, "Call started")
                            val data = response.body()?.Features
                            val allCameras = mutableListOf<CameraDetails>()
                            for (i in data!!.indices) {
                                val item = data[i].Cameras
                                allCameras.addAll(item)
                            }
                            recyclerView?.adapter = adapter
                            adapter.setData(allCameras)

                        }
                        override
                        fun onFailure(call: Call<CallResponse>, t: Throwable) {
                            //Log.e(TAG, "Call not started")
                            Toast.makeText(
                                applicationContext,
                                "Something went wrong...Please try later!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
                override fun onLost(network: Network) {
                    //Log.e(TAG, "Not connected")
                    Toast.makeText(applicationContext, R.string.connectError, Toast.LENGTH_LONG).show()
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}






