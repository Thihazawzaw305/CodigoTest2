package com.padcmyanmar.thiha.moviestreamingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewHolderMovieBinding
import com.padcmyanmar.thiha.moviestreamingapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.viewholders.MovieViewHolder

class MovieListAdapter(val mDelegate : MovieViewHolderDelegate):RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList : List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
  return MovieViewHolder(binding,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (mMovieList.isNotEmpty())
            holder.bindData(mMovieList[position])

    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }


@SuppressLint("NotifyDataSetChanged")
    fun setNewData(movielist : List<MovieVO>){
        mMovieList = movielist
        notifyDataSetChanged()
    }
}