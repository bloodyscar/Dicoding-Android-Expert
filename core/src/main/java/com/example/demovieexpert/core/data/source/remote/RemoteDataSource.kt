package com.example.demovieexpert.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demovieexpert.core.data.source.remote.network.ApiResponse
import com.example.demovieexpert.core.data.source.remote.network.ApiService
import com.example.demovieexpert.core.data.source.remote.response.ListMovieResponse
import com.example.demovieexpert.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

    }

    suspend fun getAllMovie(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MDIwOTdiMmIyZjllYzE5NGJjN2UxNTgwNjNiMGQ2NiIsInN1YiI6IjYxZjY3ZmQ3NTU5ZDIyMDBjN2M5ZWM2ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hov24JGESfmfvg8qcp_h2alJyfVW8dCMN-c4JtzKxzE"

                val response = apiService.getListMovie(token)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}