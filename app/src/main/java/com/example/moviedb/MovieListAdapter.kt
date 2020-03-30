package com.example.moviedb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MovieListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var movies = emptyList<Movie>() // Cached copy of movies

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitleView: TextView = itemView.findViewById(R.id.movieTitle)
        val movieRatingView: TextView = itemView.findViewById(R.id.movieRating)
        val moviePosterView: ImageView = itemView.findViewById(R.id.moviePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = movies[position]
        holder.movieTitleView.text = current.title
        holder.movieRatingView.text = current.vote_average.toString()
        val myOptions = RequestOptions().override(300, 450)

        Glide.with(holder.moviePosterView.context).load("https://image.tmdb.org/t/p/w500/" +current.poster_path).apply(myOptions).into(holder.moviePosterView)

    }

    internal fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun getItemCount() = movies.size
}