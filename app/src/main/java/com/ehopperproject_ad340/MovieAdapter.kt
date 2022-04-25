package com.ehopperproject_ad340


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val movies : ArrayList<MovieInfo>, private val moviesInterface: MoviesInterface) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieTitle.text = movie.title
        holder.yearPublished.text = movie.year
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.textView2)
        val yearPublished: TextView = itemView.findViewById(R.id.textView)


        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val movie = movies[position]
                val bundle = Bundle()
                bundle.putString("title", movie.title)
                bundle.putString("year", movie.year)
                bundle.putString("director", movie.director)
                bundle.putString("description", movie.description)
                moviesInterface.onCardClick(position, bundle)
            }
        }
    }
}