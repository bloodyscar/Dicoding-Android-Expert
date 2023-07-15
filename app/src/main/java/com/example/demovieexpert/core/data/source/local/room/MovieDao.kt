package com.example.demovieexpert.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demovieexpert.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAllMovie() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM Movie where isFavorite = 1")
    fun getListFavMovie() : LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteTourism(movie: MovieEntity)

}