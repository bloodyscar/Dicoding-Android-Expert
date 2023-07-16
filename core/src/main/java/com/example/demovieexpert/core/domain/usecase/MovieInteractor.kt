package com.example.demovieexpert.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.demovieexpert.core.data.Resource
import com.example.demovieexpert.core.domain.model.Movies
import com.example.demovieexpert.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepo : IMovieRepository) : MovieUseCase {
    override fun getAllMovie(): Flow<com.example.demovieexpert.core.data.Resource<List<Movies>>> = movieRepo.getAllMovie()

    override fun getFavoriteMovie(): Flow<List<Movies>> = movieRepo.getFavoriteMovie()


    override fun setFavoriteMovie(movie: Movies, state: Boolean) = movieRepo.setFavoriteMovie(movie, state)
}
