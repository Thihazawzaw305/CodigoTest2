package com.padcmyanmar.thiha.moviestreamingapp.mvp.presenters

import com.padcmyanmar.thiha.moviestreamingapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.delegates.ShowCaseViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.mvp.views.MainView

interface MainPresenter :BasePresenter , BannerViewHolderDelegate,ShowCaseViewHolderDelegate,MovieViewHolderDelegate{
    fun initView(view : MainView)
    fun onTapGenre(genrePosition : Int)
}