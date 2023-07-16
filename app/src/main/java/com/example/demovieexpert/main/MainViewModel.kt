package com.example.demovieexpert.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.demovieexpert.core.domain.usecase.MovieUseCase

class MainViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()

}