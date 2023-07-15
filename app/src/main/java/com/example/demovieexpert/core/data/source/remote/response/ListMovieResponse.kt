package com.example.demovieexpert.core.data.source.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ListMovieResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem>
) : Parcelable

@Parcelize
data class ResultsItem(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String
) : Parcelable
