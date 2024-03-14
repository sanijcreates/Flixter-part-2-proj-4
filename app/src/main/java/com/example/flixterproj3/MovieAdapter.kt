package com.example.flixterproj3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: MutableList<Movie>):RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var description: TextView = itemView.findViewById(R.id.description)
        var img: ImageView = itemView.findViewById(R.id.movie_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val listView = inflater.inflate(R.layout.movie, parent, false)
        return ViewHolder(listView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.setText(movie.title)
        holder.description.setText(movie.overview)
        Glide.with(holder.itemView)
            .load(movie.img)
            .centerCrop()
            .into(holder.img)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}