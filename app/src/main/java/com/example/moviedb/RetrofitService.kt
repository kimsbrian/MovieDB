package com.example.moviedb

import android.util.Log
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  RetrofitService {
    @GET("movie/now_playing?api_key=98f983f1a29f78051f048224030244cc&language=en-US&page=1")
    fun getMovies(): Deferred<MovieList>

    //fun getMovies( @Query("api_key") api_key: String,@Query("language") language: String,@Query("page") page: String ): Deferred<Movie>


    companion object {
        fun create(baseUrl: String): RetrofitService {

            val retrofit = Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory()).addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()))
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(RetrofitService::class.java)
        }
    }
}