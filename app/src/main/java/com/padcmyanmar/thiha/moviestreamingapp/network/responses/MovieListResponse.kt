package com.padcmyanmar.thiha.moviestreamingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.DateVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO

data class MovieListResponse (
    @SerializedName("dates")
    val dates : DateVO?,
    @SerializedName("page")
    val page : Int ?,
    @SerializedName("results")
    val results : List<MovieVO>?
        )