package com.example.tumovieapp.data.remote

import com.squareup.moshi.Json

data class MovieDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "vote_average") val rating: Double
)