package com.padcmyanmar.thiha.moviestreamingapp.data.vos

import com.google.gson.annotations.SerializedName

class ProductionCompanyVO (
@SerializedName("id")
val id : Int  =0,
@SerializedName("logo_path")
val logoPath: String?,
@SerializedName("name")
val name: String?,
@SerializedName("origin_country")
val originCountry : String?

)