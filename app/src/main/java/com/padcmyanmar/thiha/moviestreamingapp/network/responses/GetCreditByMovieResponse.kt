package com.padcmyanmar.thiha.moviestreamingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO

data class GetCreditByMovieResponse (

    @SerializedName("cast")
    val cast : List<ActorVO>?,

    @SerializedName("crew")
    val crew : List<ActorVO>?

        )