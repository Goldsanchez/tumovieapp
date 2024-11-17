package com.example.tumovieapp.domain.repository

import com.example.tumovieapp.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): Result<List<Movie>>
    suspend fun getFavoriteMovies(): Result<List<Movie>>
    suspend fun toggleFavorite(movieId: Int): Result<Unit>
}