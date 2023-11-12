package com.padcmyanmar.thiha.moviestreamingapp.mvp.views

import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO

interface MovieDetailsView {
    fun showMovieDetails(movie : MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
    fun showError(error : String)
}