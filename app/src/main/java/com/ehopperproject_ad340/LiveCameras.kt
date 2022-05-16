package com.ehopperproject_ad340

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LiveCameras : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var adapter = CameraAdapter()
    var cameraList = ArrayList<CameraDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)

        recyclerView = findViewById(R.id.recycler)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val bundle = intent.extras
        cameraList = bundle?.getParcelableArrayList<CameraDetails>("cameras") as ArrayList<CameraDetails>

        recyclerView?.adapter = adapter
        adapter.setData(cameraList)
    }
}






