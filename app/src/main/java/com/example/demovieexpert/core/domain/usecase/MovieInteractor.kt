package com.example.demovieexpert.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.demovieexpert.core.data.Resource
import com.example.demovieexpert.core.domain.model.Movies
import com.example.demovieexpert.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepo : IMovieRepository) : MovieUseCase {
    override fun getAllMovie(): LiveData<Resource<List<Movies>>> = movieRepo.getAllMovie()

    override fun getFavoriteMovie(): LiveData<List<Movies>> = movieRepo.getFavoriteMovie()


    override fun setFavoriteMovie(tourism: Movies, state: Boolean) = movieRepo.setFavoriteMovie(tourism, state)
}
