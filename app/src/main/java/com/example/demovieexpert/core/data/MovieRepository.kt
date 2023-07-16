package com.example.demovieexpert.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.demovieexpert.core.data.source.local.LocalDataSource
import com.example.demovieexpert.core.domain.repository.IMovieRepository
import com.example.demovieexpert.core.data.source.remote.RemoteDataSource
import com.example.demovieexpert.core.data.source.remote.network.ApiResponse
import com.example.demovieexpert.core.data.source.remote.response.ResultsItem
import com.example.demovieexpert.core.domain.model.Movies
import com.example.demovieexpert.core.utils.AppExecutors
import com.example.demovieexpert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

) : IMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movies, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(moviesEntity, state) }
    }


    companion object {
        @Volatile
        private var instance: MovieRepository? = null
    }


}