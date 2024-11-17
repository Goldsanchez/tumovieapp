package com.example.tumovieapp.ui.model

import com.example.tumovieapp.domain.model.Movie

sealed interface MovieUiState {
    data object Loading : MovieUiState
    data class Success(val movies: List<Movie>) : MovieUiState
    data class Error(val message: String) : MovieUiState
}