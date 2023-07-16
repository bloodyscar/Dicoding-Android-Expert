package com.example.demovieexpert.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.demovieexpert.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val getAllFavMovie = movieUseCase.getFavoriteMovie().asLiveData()
}