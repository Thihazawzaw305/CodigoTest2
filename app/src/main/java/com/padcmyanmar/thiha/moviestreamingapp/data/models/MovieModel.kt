package com.padcmyanmar.thiha.moviestreamingapp.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.GenreVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO

interface MovieModel {

    fun getNowPlayingMovies(
       // onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    ) : LiveData<List<MovieVO>>?

    fun getPopularMovies(
     //   onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getTopRatedMovies(
    //    onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getGenre(
        onSuccess : (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesByGenres(
        genreId : String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPopularActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        movieId : String,
   //     onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>?

    fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

}