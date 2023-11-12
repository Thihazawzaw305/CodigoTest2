package com.padcmyanmar.thiha.moviestreamingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewHolderShowCaseBinding
import com.padcmyanmar.thiha.moviestreamingapp.delegates.ShowCaseViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.viewholders.ShowCaseViewHolder

class ShowCaseAdapter(val mDelegate:ShowCaseViewHolderDelegate):RecyclerView.Adapter<ShowCaseViewHolder>() {

     var mTopRatedMovieList : List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCaseViewHolder {
      val binding = ViewHolderShowCaseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return ShowCaseViewHolder(binding,mDelegate)

    }

    override fun onBindViewHolder(holder: ShowCaseViewHolder, position: Int) {
        if (mTopRatedMovieList.isNotEmpty()){
            holder.setNewData(mTopRatedMovieList[position])
        }

    }

    override fun getItemCount(): Int {
     return if(mTopRatedMovieList.count() > 5)
     {
         5
     }
        else
            mTopRatedMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList : List<MovieVO>){
        mTopRatedMovieList = movieList
        notifyDataSetChanged()
    }
}