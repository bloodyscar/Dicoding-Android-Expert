package com.example.demovieexpert.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.demovieexpert.core.data.source.local.entity.MovieEntity
import com.example.demovieexpert.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun getAllMovie(): LiveData<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = movieDao.getListFavMovie()

    fun insertMovie(tourismList: List<MovieEntity>) = movieDao.insertTourism(tourismList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteTourism(movie)
    }
}