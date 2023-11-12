package com.padcmyanmar.thiha.moviestreamingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CollectionVO (
    @SerializedName("id")
    val id : Int = 0 ,

    @SerializedName("name")
    val name :String?,

    @SerializedName("poster_path")
    val posterPath : String? ,
        )