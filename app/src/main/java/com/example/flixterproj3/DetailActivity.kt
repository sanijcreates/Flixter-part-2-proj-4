package com.example.flixterproj3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val image = intent.getStringExtra("image") ?: ""
        val rating = intent.getStringExtra("rating") ?: ""
        val releaseDate = intent.getStringExtra("releaseDate") ?: ""
        val popularity = intent.getStringExtra("popularity")?: ""
        Log.d("image", image)
        val titleView = findViewById<TextView>(R.id.title1)
        val descriptionView = findViewById<TextView>(R.id.description1)
        val img =  findViewById<ImageView>(R.id.movie_image1)
        val ratingView = findViewById<TextView>(R.id.rating)
        val popularityView = findViewById<TextView>(R.id.popularity)
        val releaseDateView = findViewById<TextView>(R.id.releaseDate)

        titleView.text = title
        descriptionView.text = description
        ratingView.text = "Rating: " + rating
        popularityView.text = "Popularity: " + popularity
        releaseDateView.text = "Released on: " + releaseDate

        Glide.with(this)
            .load(image)
            .centerCrop()
            .into(img)
    }
}