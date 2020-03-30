package com.example.moviedb


import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class MovieList(
    @SerializedName("results") var movieList: List<Movie>
)