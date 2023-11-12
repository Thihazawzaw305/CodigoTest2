package com.padcmyanmar.thiha.moviestreamingapp.mvp.views

import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.GenreVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO

interface MainView: BaseView {
    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun showMoviesByGenre(moviesByGenre: List<MovieVO>)
    fun showActor(actors : List<ActorVO>)
    fun navigateToMovieDetailsScreen(movieId : Int)
}