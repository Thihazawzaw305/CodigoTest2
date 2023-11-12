package com.padcmyanmar.thiha.moviestreamingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.GenreVO

data class GenresListResponse(
    @SerializedName("genres")
    val genres : List<GenreVO>

)