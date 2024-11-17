package com.example.tumovieapp.domain.usecase

import com.example.tumovieapp.domain.model.Movie
import com.example.tumovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): Result<List<Movie>> = repository.getPopularMovies()
}