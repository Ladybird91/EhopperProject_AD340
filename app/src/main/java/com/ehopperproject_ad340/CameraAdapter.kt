package com.ehopperproject_ad340

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CameraAdapter : RecyclerView.Adapter<CameraAdapter.MyViewHolder>() {

    private var cameraList : List<CameraDetails>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.live_cam_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val camera = cameraList?.get(position)
        holder.cameraLocation.text = camera?.Description
        Picasso.get().load(camera?.getFullImageUrl()).placeholder(R.drawable.ic_baseline_image_search_24).error(R.drawable.ic_baseline_image_search_24).into(holder.image)
    }

    override fun getItemCount(): Int {
        return cameraList?.size ?: 0
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cameraLocation: TextView = itemView.findViewById(R.id.street)
        val image: ImageView = itemView.findViewById(R.id.camImage)
    }

    fun setData(cameraList : List<CameraDetails>) {
        this.cameraList = cameraList
        notifyDataSetChanged()
    }
}