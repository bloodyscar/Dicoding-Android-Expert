package com.example.demovieexpert.core.di

import FavouritedViewModel
import com.example.demovieexpert.core.domain.usecase.MovieInteractor
import com.example.demovieexpert.core.domain.usecase.MovieUseCase
import com.example.demovieexpert.detail.DetailViewModel
import com.example.demovieexpert.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { FavouritedViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}