package com.example.flixterproj3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var movies = mutableListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rvMovies)
        getMovie()
    }

    private fun getMovie() {
        val client = AsyncHttpClient()
        val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
        client.get(
            "https://api.themoviedb.org/3/movie/popular?&api_key=$apiKey",
            object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                    Log.d("sucess", json.toString())
                     val resultsJson = json.jsonObject.getJSONArray("results")
                     Log.d("sad", resultsJson.toString())

                     for(i in 0 until resultsJson.length()) {
                         val movieObject = resultsJson.getJSONObject(i)
                         val title = movieObject.getString("original_title")
                         val overview = movieObject.getString("overview")
                         val img = "https://image.tmdb.org/t/p/w500" + movieObject.getString("poster_path")
                         val rating = movieObject.getString("vote_average")
                         val releaseDate = movieObject.getString("release_date")
                         val popularity = movieObject.getString("popularity")
                         movies.add(Movie(title, overview, img, rating, releaseDate, popularity))
                     }

                     recyclerView.adapter = MovieAdapter(movies, this@MainActivity) { position->
                         Log.d("posiiton", position.toString())
                         val clickedItem = movies[position]
                         val intent = Intent(this@MainActivity, DetailActivity::class.java)
                         intent.putExtra("title", clickedItem.title)
                         intent.putExtra("description", clickedItem.overview)
                         intent.putExtra("image", clickedItem.img)
                         intent.putExtra("rating", clickedItem.rating)
                         intent.putExtra("popularity", clickedItem.popularity)
                         intent.putExtra("releaseDate", clickedItem.releaseDate)
                         Log.d("clickedItem", clickedItem.toString())
                         startActivity(intent)
                     }
                     recyclerView.layoutManager  = LinearLayoutManager(this@MainActivity)



                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String,
                    throwable: Throwable?

                ) {
                    Log.d("failer", "response")
                }
            })
    }
}
