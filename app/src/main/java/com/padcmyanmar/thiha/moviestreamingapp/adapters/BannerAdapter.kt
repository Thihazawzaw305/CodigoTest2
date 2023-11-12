package com.padcmyanmar.thiha.moviestreamingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewItemBannerBinding
import com.padcmyanmar.thiha.moviestreamingapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.viewholders.BannerItemView

class BannerAdapter(val mDelegate: BannerViewHolderDelegate) : RecyclerView.Adapter<BannerItemView>() {

    private var mMovieList: List<MovieVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerItemView {
        val binding = ViewItemBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return BannerItemView(binding, mDelegate)

    }

    override fun onBindViewHolder(holder: BannerItemView, position: Int) {
        if (mMovieList.isNotEmpty())
            holder.bindData(mMovieList[position])
    }

    override fun getItemCount(): Int {
        return if(mMovieList.count()>5)
            5
       else
           mMovieList.count()
    }

    @Suppress("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }


}