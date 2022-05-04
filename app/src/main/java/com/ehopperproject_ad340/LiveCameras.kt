package com.ehopperproject_ad340


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LiveCameras : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_cameras)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler2)
        var adapter = CameraAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter


        val call: Call<GetList> = CamApi.retrofitService.getProperties()
        call.enqueue(object : Callback<GetList> {
            override
            fun onResponse(call: Call<GetList>, response: Response<GetList>) {
                val features = response.body()!!
                val allCameras = mutableListOf<Camera>()
                for (camera in features.Features) {
                    allCameras.addAll(camera.Cameras)
                }
                adapter.setData(allCameras)
                recyclerView?.adapter = adapter
            }
            override
            fun onFailure(call: Call<GetList>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}






