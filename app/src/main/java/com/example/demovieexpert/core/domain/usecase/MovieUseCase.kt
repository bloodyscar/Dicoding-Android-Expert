package com.example.demovieexpert.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.demovieexpert.core.data.Resource
import com.example.demovieexpert.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovie(): Flow<List<Movies>>
    fun setFavoriteMovie(movie: Movies, state: Boolean)
}