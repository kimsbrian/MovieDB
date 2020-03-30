package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

private lateinit var movieViewModel: MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var job: Job
    private val apiKey = "98f983f1a29f78051f048224030244cc"
    private val movies = ArrayList<Movie>()

    private val retrofitService by lazy {
        RetrofitService.create(getString(R.string.base_url))
    }

    private suspend fun fetchMovies(pageNumber:String){
        //val request =   retrofitService.getMovies( apiKey,"en-US",pageNumber)
        val request =   retrofitService.getMovies()

        try {
            val movieList = request.await()
            for(movie in movieList.movieList){
                movieViewModel.insert(movie)
            }

            Log.d("retrofit_demo", "movie $movieList")
            // Do something with the response.body()
        } catch (e: HttpException) {
            Log.d("retrofit_demo", "exception:$e")
        } catch (e: Throwable) {
            Log.d("retrofit_demo",e.toString())
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = MovieListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.allMovies.observe(this, Observer { movies ->
            // Update the cached copy of the words in the adapter.
            movies?.let { adapter.setMovies(it) }
        })
        job =  CoroutineScope(Dispatchers.IO).launch {
            fetchMovies("1")
        }
    }

}
