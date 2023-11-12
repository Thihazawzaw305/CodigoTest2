package com.padcmyanmar.thiha.moviestreamingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.moviestreamingapp.persistence.typeconverters.*


@Entity(tableName = "movies")

@TypeConverters(
  CollectionTypeConverter::class,
    GenreListTypeConverter::class,
    GenreIdTypeConverter::class,
    ProductionCompanyConverter::class,
    ProductionCountryConverter::class,
    SpokenLanguageConverter::class
)



data class MovieVO(


    @ColumnInfo(name = "is_favourite")
    @SerializedName("is_favourite")
    val isFavourite: Boolean = false,


    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean?,


    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,


    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,


    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,


    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,


    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,


    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String?,


    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,


    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,


    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,


    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,


    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float?,


    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,


    @SerializedName("belongs_to_collection")
    @ColumnInfo(name = "belongs_to_collection")
    val belongsToCollection: CollectionVO?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<GenreVO>?,

    @SerializedName("production_companies")
    @ColumnInfo(name = "production_companies")
    val productionCompanies: List<ProductionCompanyVO>?,

    @SerializedName("production_countries")
    @ColumnInfo(name = "production_countries")
    val productionCountries: List<ProductionCountryVO>?,

    @SerializedName("spoken_languages")
    @ColumnInfo(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageVO>?,

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runTime : Int ?,

    @ColumnInfo(name = "type")
    var type: String?
)
{
    fun getRatingBaseOnFiveStars(): Float {
        return voteAverage?.div(2) ?: 0.0f
    }

    fun calculateRunTime() : String{
        val movieRunTime : String
        val hour : String = runTime?.div(60).toString()
        val min : String = runTime?.rem(60).toString()
        movieRunTime = "$hour h $min min"
        return movieRunTime
    }

    fun getGenresAsCommaSeparatedString(): String {
        return genres?.map { it.name }?.joinToString(",") ?: ""
    }
}
const val NOW_PLAYING = "NOW_PLAYING"
const val POPULAR = "POPULAR"
const val TOP_RATED = "TOP_RATED"

//
//"adult": false,
//"backdrop_path": "/22z44LPkMyf5nyyXvv8qQLsbom.jpg",
//"genre_ids": [
//27,
//9648,
//53
//],
//"id": 631842,
//"original_language": "en",
//"original_title": "Knock at the Cabin",
//"overview": "While vacationing at a remote cabin, a young girl and her two fathers are taken hostage by four armed strangers who demand that the family make an unthinkable choice to avert the apocalypse. With limited access to the outside world, the family must decide what they believe before all is lost.",
//"popularity": 3012.719,
//"poster_path": "/dm06L9pxDOL9jNSK4Cb6y139rrG.jpg",
//"release_date": "2023-02-01",
//"title": "Knock at the Cabin",
//"video": false,
//"vote_average": 6.5,
//"vote_count": 907