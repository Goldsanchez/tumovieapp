package com.example.tumovieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tumovieapp.domain.usecase.GetFavoriteMoviesUseCase
import com.example.tumovieapp.domain.usecase.GetPopularMoviesUseCase
import com.example.tumovieapp.domain.usecase.ToggleFavoriteMovieUseCase
import com.example.tumovieapp.ui.model.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _isShowingFavorites = MutableStateFlow(false)
    val isShowingFavorites = _isShowingFavorites.asStateFlow()

    init {
        loadMovies()
    }

    fun toggleFavoriteFilter() {
        _isShowingFavorites.value = !_isShowingFavorites.value
        loadMovies()
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            toggleFavoriteMovieUseCase(movieId)
                .onSuccess { loadMovies() }
                .onFailure { error ->
                    _uiState.value = MovieUiState.Error(error.message ?: "Unknown error")
                }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _uiState.value = MovieUiState.Loading
            val result = if (_isShowingFavorites.value) {
                getFavoriteMoviesUseCase()
            } else {
                getPopularMoviesUseCase()
            }

            result.onSuccess { movies ->
                _uiState.value = MovieUiState.Success(movies)
            }.onFailure { error ->
                _uiState.value = MovieUiState.Error(error.message ?: "Unknown error")
            }
        }
    }
}