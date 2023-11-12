package com.padcmyanmar.thiha.moviestreamingapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.thiha.moviestreamingapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.mvp.views.MovieDetailsView

interface MovieDetailsPresenter : BasePresenter, MovieViewHolderDelegate{
    fun initView(view : MovieDetailsView)
    fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int)
    fun onTapBack()
}