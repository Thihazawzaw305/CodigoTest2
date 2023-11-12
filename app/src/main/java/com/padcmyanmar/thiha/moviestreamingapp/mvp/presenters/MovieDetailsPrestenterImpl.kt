package com.padcmyanmar.thiha.moviestreamingapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModel
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModelImpl
import com.padcmyanmar.thiha.moviestreamingapp.mvp.views.MovieDetailsView
import com.padcmyanmar.thiha.moviestreamingapp.persistence.MovieDatabase

class MovieDetailsPrestenterImpl: ViewModel(), MovieDetailsPresenter {
    var mMovieDatabase: MovieDatabase? = null
    // View
    private var mView: MovieDetailsView? = null
    // model
    private val mMovieModel : MovieModel = MovieModelImpl

    override fun initView(view: MovieDetailsView) {
      mView = view
    }



    override fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int) {
// Movie Details
        mMovieModel.getMovieDetails(movieId.toString()){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetails(it)
            }
        }
        // Credit

        mMovieModel.getCreditByMovie(movieId = movieId.toString(), onSuccess = {
            mView?.showCreditsByMovie(cast = it.first, crew = it.second)
        }, onFailure = {
            mView?.showError(it)
        })
    }




    override fun onTapBack() {
      mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapMovie(movieId: Int) {

    }

    override fun toggleFavourite(id: Int, isFavourite: Boolean) {
        Log.d("toggle","$id -- $isFavourite")
        val movieVO = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(id)
        movieVO?.let { mMovieDatabase?.movieDao()?.insertSingleMovie(it.copy(isFavourite = !isFavourite)) }
    }
}