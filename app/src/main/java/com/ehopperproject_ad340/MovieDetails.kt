package com.ehopperproject_ad340

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        val movieTitle = findViewById<TextView>(R.id.movieDetTitle)
        val movieDirector = findViewById<TextView>(R.id.movieDirector)
        val movieYear = findViewById<TextView>(R.id.movieYear)
        val movieDescription = findViewById<TextView>(R.id.movieDescription)
        movieDescription.movementMethod = ScrollingMovementMethod()
        val bundle = intent.extras
        val title = bundle?.getString("title")
        val director = bundle?.getString("director")
        val year = bundle?.getString("year")
        val description = bundle?.getString("description")
        movieTitle.text = title
        movieDirector.text = director
        movieYear.text = year
        movieDescription.text = description
    }
}