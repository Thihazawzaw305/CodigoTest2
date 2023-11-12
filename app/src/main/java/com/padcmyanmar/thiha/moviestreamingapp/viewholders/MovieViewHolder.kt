package com.padcmyanmar.thiha.moviestreamingapp.viewholders


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewHolderMovieBinding
import com.padcmyanmar.thiha.moviestreamingapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.utils.IMAGE_BASE_URL


class MovieViewHolder(private val binding: ViewHolderMovieBinding, private val delegate: MovieViewHolderDelegate) :
    RecyclerView.ViewHolder(binding.root) {

    private var mMovie: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let {
                delegate.onTapMovie(it.id)
            }

        }
        binding.btnFavFromMovie.setOnClickListener{
            mMovie?.let {
                delegate.toggleFavourite(it.id!!, it.isFavourite)
            }


    }}


    fun bindData(movie: MovieVO) {
        mMovie = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(binding.ivMovieImage)

        binding.tvMovieName.text = movie.title
        binding.tvMovieRating.text = movie.voteAverage.toString()
        binding.rbrMovieRating.rating = movie.getRatingBaseOnFiveStars()
        if(movie.isFavourite){
            Glide.with(itemView.context)
                .load(R.drawable.baseline_favorite_24)
                .into(binding.btnFavFromMovie)
        }else{
            Glide.with(itemView.context)
                .load(R.drawable.ic_baseline_favorite_border_24_white)
                .into(binding.btnFavFromMovie)
        }

    }
    }


