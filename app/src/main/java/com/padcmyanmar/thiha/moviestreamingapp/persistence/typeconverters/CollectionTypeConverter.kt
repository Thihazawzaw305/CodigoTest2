package com.padcmyanmar.thiha.moviestreamingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.CollectionVO

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collection: CollectionVO?) : String{
        return Gson().toJson(collection)
    }


    @TypeConverter
    fun toCollection(collectionJSONString : String) : CollectionVO?{
        val collection = object  : TypeToken<CollectionVO?>(){}.type
        return Gson().fromJson(collectionJSONString,collection)
    }
}