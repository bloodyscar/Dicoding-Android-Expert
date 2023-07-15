package com.example.demovieexpert.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.demovieexpert.core.domain.model.Movies
import com.example.demovieexpert.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movies, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)


}