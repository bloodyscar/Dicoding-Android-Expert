package com.example.demovieexpert.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demovieexpert.core.data.source.remote.network.ApiResponse
import com.example.demovieexpert.core.data.source.remote.network.ApiService
import com.example.demovieexpert.core.data.source.remote.response.ListMovieResponse
import com.example.demovieexpert.core.data.source.remote.response.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllMovie(): LiveData<ApiResponse<List<ResultsItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<ResultsItem>>>()

        //get data from remote api
        val client = apiService.getListMovie()

        client.enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                val dataArray = response.body()?.results
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }
}