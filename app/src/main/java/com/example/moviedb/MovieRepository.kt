package com.example.moviedb

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class MovieRepository(private val movieDao: MovieDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allMovies: LiveData<List<Movie>> = movieDao.getAlphabetizedMovies()


    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

}