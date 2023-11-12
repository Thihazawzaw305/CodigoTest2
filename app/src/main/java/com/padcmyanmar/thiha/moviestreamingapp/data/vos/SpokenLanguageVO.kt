package com.padcmyanmar.thiha.moviestreamingapp.data.vos

import com.google.gson.annotations.SerializedName

class SpokenLanguageVO(

    @SerializedName("english_name")
    val englishName: String?,

    @SerializedName("iso_639_1")
    val iso_639_1 : String?,

    @SerializedName("name")
    val name : String?

)