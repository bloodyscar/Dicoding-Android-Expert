package com.example.demovieexpert.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.demovieexpert.core.data.Resource
import com.example.demovieexpert.core.domain.model.Movies

interface IMovieRepository {
    fun getAllMovie(): LiveData<Resource<List<Movies>>>
    fun getFavoriteMovie(): LiveData<List<Movies>>
    fun setFavoriteMovie(tourism: Movies, state: Boolean)


}