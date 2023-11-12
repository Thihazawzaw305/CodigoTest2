package com.padcmyanmar.thiha.moviestreamingapp.network

import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.network.responses.ActorListResponse
import com.padcmyanmar.thiha.moviestreamingapp.network.responses.GenresListResponse
import com.padcmyanmar.thiha.moviestreamingapp.network.responses.GetCreditByMovieResponse
import com.padcmyanmar.thiha.moviestreamingapp.network.responses.MovieListResponse
import com.padcmyanmar.thiha.moviestreamingapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(API_GET_GENRES)
    fun getGenre(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        ):Observable<GenresListResponse>

    @GET(API_GET_MOVIES_BY_GENRE)
    fun getMovieByGenres(
        @Query(PARAM_GENRE_ID) genreId : String ,
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY
    ):Observable<MovieListResponse>


    @GET(API_GET_ACTORS)
    fun getPopularActors(
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page : Int = 1
    ):Observable<ActorListResponse>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,
    ):Observable<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditByMovie(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ):Observable<GetCreditByMovieResponse>
}

