package com.example.tumovieapp.domain.usecase

import com.example.tumovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class ToggleFavoriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Unit> = repository.toggleFavorite(movieId)
}