package com.example.demovieexpert.core.utils

import com.example.demovieexpert.core.data.source.local.entity.MovieEntity
import com.example.demovieexpert.core.data.source.remote.response.ResultsItem
import com.example.demovieexpert.core.domain.model.Movies

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val tourismList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                title = it.title,
                overview = it.overview,
                poster = it.posterPath,
                isFavorite = false
            )
            tourismList.add(movie)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movies> =
        input.map {
            Movies(
                title = it.title,
                overview = it.overview,
                poster = it.poster,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movies) = MovieEntity(
        title = input.title,
        overview = input.overview,
        poster = input.poster,
        isFavorite = input.isFavorite

    )
}