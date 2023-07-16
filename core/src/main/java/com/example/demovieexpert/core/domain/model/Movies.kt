package com.example.demovieexpert.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    var title : String,
    var poster : String,
    var overview : String,
    val isFavorite: Boolean
) : Parcelable

