package com.padcmyanmar.thiha.moviestreamingapp.data.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BaseModel(), MovieModel {
    // Data Agent
    //  val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    //Database
    //  private var mMovieDatabase: MovieDatabase? = null

//    fun initDatabase(context: Context) {
//        mMovieDatabase = MovieDatabase.getDBInstance(context)
//    }

    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {

        mMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
                //   onSuccess(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING)
    }

    @SuppressLint("CheckResult")
    override fun getPopularMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        mMovieApi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//                onSuccess(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR)


    }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(

        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {


        mMovieApi.getTopRatedMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
                //               onSuccess(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = TOP_RATED)
    }

    @SuppressLint("CheckResult")
    override fun getGenre(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
//        mMovieDataAgent.getGenre(onSuccess = onSuccess, onFailure = onFailure)
        mMovieApi.getGenre()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genres)

            }, { onFailure(it.localizedMessage ?: "") })
    }

    @SuppressLint("CheckResult")
    override fun getMoviesByGenres(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getMovieByGenres(
//            genreId = genreId,
//            onSuccess = onSuccess,
//            onFailure = onFailure
        //       )
        mMovieApi.getMovieByGenres(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.let { movies ->
                    onSuccess(movies)
                }
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getPopularActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {

        //   mMovieDataAgent.getPopularActors(onSuccess = onSuccess, onFailure = onFailure)
        mMovieApi.getPopularActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.let { actors ->
                    onSuccess(actors)
                }
            }, { onFailure(it.localizedMessage ?: "") })
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetails(
        movieId: String,

        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {
        mMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabaseToSync =
                    mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
//                onSuccess(it)
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    @SuppressLint("CheckResult")
    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getCreditByMovie(movieId, onSuccess = onSuccess, onFailure = onFailure)
        mMovieApi.getCreditByMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))
            }, { onFailure(it.localizedMessage ?: "") })
    }
}