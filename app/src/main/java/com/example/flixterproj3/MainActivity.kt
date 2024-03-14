package com.example.flixterproj3

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
            "https://api.themoviedb.org/3/movie/now_playing?&api_key=$apiKey",
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
                         Log.d("img", img)
                         movies.add(Movie(title, overview, img))
                        Log.d("single", title + " " + overview)
                     }

                     val adapter = MovieAdapter(movies)
                     recyclerView.adapter = adapter
                     recyclerView.layoutManager  = LinearLayoutManager(this@MainActivity)

                    
                     Log.d("ASda", resultsJson[1].toString())

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
