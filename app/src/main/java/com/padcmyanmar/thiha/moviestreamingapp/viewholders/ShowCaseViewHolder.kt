package com.padcmyanmar.thiha.moviestreamingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewHolderShowCaseBinding
import com.padcmyanmar.thiha.moviestreamingapp.delegates.ShowCaseViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.utils.IMAGE_BASE_URL


class ShowCaseViewHolder(private val binding: ViewHolderShowCaseBinding,private val delegate : ShowCaseViewHolderDelegate):RecyclerView.ViewHolder(binding.root) {

    private var mMovie : MovieVO ? = null
init {
    itemView.setOnClickListener {
        mMovie?.let {
            delegate.onTapMovieFromShowCase(it.id)
        }

    }
}

    fun setNewData(movie : MovieVO){
        mMovie = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(binding.ivShowCase)
        binding.tvShowCaseMovieName.text = movie.originalTitle
        binding.tvShowCaseMovieDate.text = movie.releaseDate
    }
}