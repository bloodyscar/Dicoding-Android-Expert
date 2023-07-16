package com.example.demovieexpert.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.demovieexpert.core.data.Resource
import com.example.demovieexpert.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<com.example.demovieexpert.core.data.Resource<List<Movies>>>
    fun getFavoriteMovie(): Flow<List<Movies>>
    fun setFavoriteMovie(movie: Movies, state: Boolean)


}