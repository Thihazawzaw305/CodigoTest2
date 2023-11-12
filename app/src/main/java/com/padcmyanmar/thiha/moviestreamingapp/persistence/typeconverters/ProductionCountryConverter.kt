package com.padcmyanmar.thiha.moviestreamingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ProductionCompanyVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ProductionCountryVO

class ProductionCountryConverter {
    @TypeConverter
    fun toString(productionCountriesList : List<ProductionCountryVO>? ): String{
        return Gson().toJson(productionCountriesList)
    }

    @TypeConverter
    fun toProductionCountriesList(productionCountriesJSONString : String ):  List<ProductionCountryVO>?{
        val productionCountriesList = object : TypeToken<List<ProductionCountryVO>?>(){}.type
        return Gson().fromJson(productionCountriesJSONString,productionCountriesList)
    }
}