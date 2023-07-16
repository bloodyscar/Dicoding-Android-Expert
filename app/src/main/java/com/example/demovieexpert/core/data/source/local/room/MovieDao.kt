package com.example.demovieexpert.core.data.source.local.room

import kotlinx.coroutines.flow.Flow
import androidx.room.*
import com.example.demovieexpert.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAllMovie() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM Movie where isFavorite = 1")
    fun getListFavMovie() : Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

}