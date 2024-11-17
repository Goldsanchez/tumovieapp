package com.example.tumovieapp.data.repository

import com.example.tumovieapp.core.constants.Constants.API_KEY
import com.example.tumovieapp.data.local.MovieDao
import com.example.tumovieapp.data.mapper.MovieMapper.toDomain
import com.example.tumovieapp.data.mapper.MovieMapper.toEntity
import com.example.tumovieapp.data.remote.MovieApiService
import com.example.tumovieapp.domain.model.Movie
import com.example.tumovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApiService,
    private val dao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(): Result<List<Movie>> = runCatching {
        val remoteMovies = api.getPopularMovies(API_KEY).movies.map { it.toDomain() }
        dao.insertMovies(remoteMovies.map { it.toEntity() })
        dao.getAllMovies().map { it.toDomain() }
    }

    override suspend fun getFavoriteMovies(): Result<List<Movie>> = runCatching {
        dao.getFavoriteMovies().map { it.toDomain() }
    }

    override suspend fun toggleFavorite(movieId: Int): Result<Unit> = runCatching {
        val movies = dao.getAllMovies()
        val movie = movies.first { it.id == movieId }
        dao.updateFavorite(movieId, !movie.isFavorite)
    }
}