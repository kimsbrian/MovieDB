package com.example.moviedb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class MovieViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: MovieRepository
    // LiveData gives us updated movies when they change.
    val allMovies: LiveData<List<Movie>>

    init {
        // Gets reference to MovieDao from MovieRoomDatabase to construct
        // the correct MovieRepository. 
        val moviesDao = MovieRoomDatabase.getDatabase(application, viewModelScope).movieDao()
        repository = MovieRepository(moviesDao)
        allMovies = repository.allMovies
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(movie: Movie) = viewModelScope.launch {
        repository.insert(movie)
    }
}