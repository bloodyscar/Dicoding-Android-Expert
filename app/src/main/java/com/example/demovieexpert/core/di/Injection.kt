package com.example.demovieexpert.core.di

import android.content.Context
import com.example.demovieexpert.core.data.MovieRepository
import com.example.demovieexpert.core.data.source.local.LocalDataSource
import com.example.demovieexpert.core.data.source.local.room.MovieDatabase
import com.example.demovieexpert.core.data.source.remote.RemoteDataSource
import com.example.demovieexpert.core.data.source.remote.network.ApiConfig
import com.example.demovieexpert.core.domain.repository.IMovieRepository
import com.example.demovieexpert.core.domain.usecase.MovieInteractor
import com.example.demovieexpert.core.domain.usecase.MovieUseCase
import com.example.demovieexpert.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}