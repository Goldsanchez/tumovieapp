package com.example.tumovieapp.data.mapper

import com.example.tumovieapp.data.local.MovieEntity
import com.example.tumovieapp.data.remote.MovieDto
import com.example.tumovieapp.domain.model.Movie

object MovieMapper {
    fun MovieDto.toDomain() = Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        rating = rating
    )

    fun MovieEntity.toDomain() = Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        rating = rating,
        isFavorite = isFavorite
    )

    fun Movie.toEntity() = MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        rating = rating,
        isFavorite = isFavorite
    )
}