package com.padcmyanmar.thiha.moviestreamingapp.data.vos

import com.google.gson.annotations.SerializedName

data class ActorVO (
    @SerializedName("name")
    val name : String?,
    @SerializedName("profile_path")
    val profilePath : String?,
        )