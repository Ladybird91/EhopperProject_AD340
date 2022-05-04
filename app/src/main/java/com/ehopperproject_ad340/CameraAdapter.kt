package com.ehopperproject_ad340

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CameraAdapter() : RecyclerView.Adapter<CameraAdapter.MyViewHolder>() {

    private var cameraList : List<Camera>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.live_cam_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val camera = cameraList?.get(position)
        holder.cameraLocation.text = camera?.Description
    }

    override fun getItemCount(): Int {
        return cameraList?.size ?: 0
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cameraLocation: TextView = itemView.findViewById(R.id.street)
        //val image: ImageView = itemView.findViewById(R.id.imageView)
    }

    fun setData(cameraList : List<Camera>) {
        this.cameraList = cameraList
        notifyDataSetChanged()
    }
}