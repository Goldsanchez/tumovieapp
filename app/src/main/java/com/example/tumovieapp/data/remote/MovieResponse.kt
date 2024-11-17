package com.example.tumovieapp.data.remote

import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "results") val movies: List<MovieDto>
)