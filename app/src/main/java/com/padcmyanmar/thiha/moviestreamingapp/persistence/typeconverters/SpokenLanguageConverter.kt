package com.padcmyanmar.thiha.moviestreamingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ProductionCountryVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.SpokenLanguageVO

class SpokenLanguageConverter {
    @TypeConverter
    fun toString(spokenLanguageList : List<SpokenLanguageVO>? ): String{
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun tospokenLanguageList(spokenLanguageListJSONString : String ):  List<SpokenLanguageVO>?{
        val spokenLanguageList = object : TypeToken<List<SpokenLanguageVO>? >(){}.type
        return Gson().fromJson(spokenLanguageListJSONString,spokenLanguageList)
    }

}