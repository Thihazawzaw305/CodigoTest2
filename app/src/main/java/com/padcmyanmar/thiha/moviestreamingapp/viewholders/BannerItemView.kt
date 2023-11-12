package com.padcmyanmar.thiha.moviestreamingapp.viewholders

import android.util.Log.w
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewItemBannerBinding
import com.padcmyanmar.thiha.moviestreamingapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.utils.IMAGE_BASE_URL


class BannerItemView(private val binding: ViewItemBannerBinding, private val delegate: BannerViewHolderDelegate) :
    RecyclerView.ViewHolder(binding.root) {

    private var mMovie: MovieVO? = null

    init {
        itemView.setOnClickListener {
         mMovie?.let   {
             delegate.onTapMovieFromBanner(it.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovie = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(binding.ivBannerImage)

        binding.tvBannerMovieName.text = movie.title
    }

}