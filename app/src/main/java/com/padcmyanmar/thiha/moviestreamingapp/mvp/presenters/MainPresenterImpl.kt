package com.padcmyanmar.thiha.moviestreamingapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModel
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModelImpl
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.GenreVO
import com.padcmyanmar.thiha.moviestreamingapp.mvp.views.MainView
import com.padcmyanmar.thiha.moviestreamingapp.persistence.MovieDatabase

class MainPresenterImpl: ViewModel(), MainPresenter {
    //view
    var mView : MainView? = null
    // model
    private val mMovieModel : MovieModel = MovieModelImpl

    // states
    private var mGenres : List<GenreVO> = listOf()

    override fun initView(view: MainView) {
      mView = view
    }
    var mMovieDatabase: MovieDatabase? = null

    override fun onTapGenre(genrePosition: Int) {

    }

    override fun onUiReady(owner: LifecycleOwner) {
        //now playing

          mMovieModel.getNowPlayingMovies {
              mView?.showError(it)
          }?.observe(owner){
              mView?.showNowPlayingMovies(it)
          }


      // get popular
            mMovieModel.getPopularMovies {
                mView?.showError(it)
            }?.observe(owner){
                mView?.showPopularMovies(it)
            }


       // get top rate
            mMovieModel.getTopRatedMovies {
                mView?.showError(it)
            }?.observe(owner){
                mView?.showTopRatedMovies(it)
            }

        mMovieModel.getGenre(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id.let { firstGenreId ->
                    if (firstGenreId != null) {
                        onTapGenre(firstGenreId)
                    }
                }
            }, onFailure = {
                mView?.showError(it)
            }
        )

            mMovieModel.getPopularActors(
                onSuccess = {
                    mView?.showActor(it)

                },
                onFailure = {
                    mView?.showError(it)
                }
            )

    }

    override fun onTapMovieFromBanner(movieId: Int) {
       mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovieFromShowCase(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun toggleFavourite(id: Int, isFavourite: Boolean) {
        Log.d("toggle","$id -- $isFavourite")
        val movieVO = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(id)
        movieVO?.let { mMovieDatabase?.movieDao()?.insertSingleMovie(it.copy(isFavourite = !isFavourite)) }
    }
}