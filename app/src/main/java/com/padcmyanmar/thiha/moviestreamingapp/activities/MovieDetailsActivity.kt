package com.padcmyanmar.thiha.moviestreamingapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModel
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModelImpl
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.GenreVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ActivityMovieDetailsBinding
import com.padcmyanmar.thiha.moviestreamingapp.mvp.views.MovieDetailsView
import com.padcmyanmar.thiha.moviestreamingapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.thiha.moviestreamingapp.viewpods.ActorListViewPod


class MovieDetailsActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMovieDetailsBinding
    lateinit var mActorListViewPod: ActorListViewPod
    lateinit var mCreatorListViewPod: ActorListViewPod
    private val mMovieModel: MovieModel = MovieModelImpl

    companion object {

        const val MOVIE_ID = "MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpViewPod()
        setUpListener()
        val movieId = intent?.getIntExtra(MOVIE_ID, 0)

        if (movieId != null) {
            requestData(movieId)
        }
    }


    private fun requestData(movieId: Int) {
        mMovieModel.getMovieDetails(movieId = movieId.toString(),
           onFailure = {
            Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
        })?.observe(this){
            it?.let { movieDetails -> bindData(movieDetails) }
        }

        mMovieModel.getCreditByMovie(movieId = movieId.toString(),
        onSuccess = {
            mActorListViewPod.setData(it.first)
            mCreatorListViewPod.setData(it.second)
        },
        onFailure = {

        })
    }


    @SuppressLint("SetTextI18n")
    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(binding.ivMovieImageFromMovieDetails)

        binding.tvMovieNameFromMovieDetails.text = movie.originalTitle?:""
        binding.tvMovieReleaseDate.text = movie.releaseDate?.substring(0,4)
        binding.rbRatingBar.rating = movie.getRatingBaseOnFiveStars()
        binding.tvVoteCount.text = "${movie.voteCount} VOTES"
        binding.tvMovieRating.text = (movie.voteAverage ?: 0.0f).toString()
        binding.collapsingToolBarLayout.title = movie.title?:""
        binding.tvTime.text = movie.calculateRunTime()
        bindGenre(movie,movie.genres?: listOf())
        binding.tvOverView.text = movie.overview?:""
        binding.tvOriginalTitle.text = movie.originalTitle?:""
        binding.tvType.text = movie.getGenresAsCommaSeparatedString()
        binding.tvPremiere.text = movie.releaseDate?:""
        binding.tvDescription.text = movie.overview?:""
        if(movie.isFavourite){
            Glide.with(this)
                .load(R.drawable.baseline_favorite_24)
                .into(binding.ivFav)
        }else{
            Glide.with(this)
                .load(R.drawable.ic_baseline_favorite_border_24_white)
                .into(binding.ivFav)
        }



    }


    private fun bindGenre(movie : MovieVO, genre : List<GenreVO>){

        movie.genres?.count()?.let {
            binding.tvFirstGenre.text = genre.firstOrNull()?.name?:""
            binding.tvSecondGenre.text = genre.getOrNull(1)?.name?:""
            binding.tvThirdGenre.text = genre.getOrNull(2)?.name?:""

            if (it < 3)
                binding.tvThirdGenre.visibility = View.GONE
            else if( it <2)
                binding.tvSecondGenre.visibility = View.GONE
        }

    }

    private fun setUpViewPod() {
        val vpActorsListFromMovieDetails = findViewById<ActorListViewPod>(R.id.vpActorsListFromMovieDetails)
        mActorListViewPod = vpActorsListFromMovieDetails as ActorListViewPod
        mActorListViewPod.setUpActorViewPod(
            R.color.colorPrimary,
            getString(R.string.lbl_actors),
            ""
        )

        val vpCreatorsListFromMovieDetails = findViewById<ActorListViewPod>(R.id.vpCreatorsListFromMovieDetails)
        mCreatorListViewPod = vpCreatorsListFromMovieDetails as ActorListViewPod
        mCreatorListViewPod.setUpActorViewPod(
            R.color.colorPrimary,
            getString(R.string.lbl_creators),
            getString(R.string.lbl_more_creators)
        )

    }

    private fun setUpListener() {
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }


}