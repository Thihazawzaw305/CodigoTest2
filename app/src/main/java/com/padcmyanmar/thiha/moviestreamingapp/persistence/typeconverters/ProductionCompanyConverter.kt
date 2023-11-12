package com.padcmyanmar.thiha.moviestreamingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ProductionCompanyVO

class ProductionCompanyConverter {
    @TypeConverter
    fun toString(productionCompaniesList : List<ProductionCompanyVO>? ): String{
        return Gson().toJson(productionCompaniesList)
    }

    @TypeConverter
    fun toProductionCompanyList(productionCompanJSONString : String ):  List<ProductionCompanyVO>?{
        val productionCompaniesList = object : TypeToken<List<ProductionCompanyVO>?>(){}.type
        return Gson().fromJson(productionCompanJSONString,productionCompaniesList)
    }
}