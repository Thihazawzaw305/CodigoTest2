package com.padcmyanmar.thiha.moviestreamingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO

data class ActorListResponse (
    @SerializedName("results")
    val results : List<ActorVO>?
)