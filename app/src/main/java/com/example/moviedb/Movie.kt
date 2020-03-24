package com.example.moviedb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey @ColumnInfo(name = "movie_name") val movieName: String,
    @ColumnInfo(name = "poster_url") val posterURL: String?,
    @ColumnInfo(name = "rating") val rating: Int?,
    @ColumnInfo(name = "release_data") val releaseDate: String?,
    @ColumnInfo(name = "summary_text") val summaryText: String?
)