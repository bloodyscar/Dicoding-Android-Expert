package com.example.demovieexpert.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
class MovieEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    var title : String = "",

    @ColumnInfo(name = "poster")
    var poster : String = "",

    @ColumnInfo(name = "overview")
    var overview : String = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)