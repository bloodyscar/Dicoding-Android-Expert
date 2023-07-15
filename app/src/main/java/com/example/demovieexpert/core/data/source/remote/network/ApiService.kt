package com.example.demovieexpert.core.data.source.remote.network

import com.example.demovieexpert.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular")
    fun getListMovie(
    ): Call<ListMovieResponse>
}