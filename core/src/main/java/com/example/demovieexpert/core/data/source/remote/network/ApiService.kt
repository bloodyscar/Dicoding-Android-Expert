package com.example.demovieexpert.core.data.source.remote.network

import com.example.demovieexpert.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("movie/popular")
    suspend fun getListMovie(
        @Header("Authorization") token: String,
        ): ListMovieResponse
}