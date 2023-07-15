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

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

) : IMovieRepository {
    override fun getAllMovie(): LiveData<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movies>> {
                return Transformations.map(localDataSource.getAllMovie()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
//                data == null || data.isEmpty()
                false // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllMovie()

            override fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()

    override fun getFavoriteMovie(): LiveData<List<Movies>> {
        return Transformations.map(localDataSource.getFavoriteMovie()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(tourism: Movies, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(tourismEntity, state) }
    }


    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, localDataSource, appExecutors)
            }.also { instance = it }
    }


}