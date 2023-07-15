package com.example.demovieexpert.presentation.main

import androidx.lifecycle.ViewModel
import com.example.demovieexpert.core.domain.usecase.MovieUseCase

class MainViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie()

}