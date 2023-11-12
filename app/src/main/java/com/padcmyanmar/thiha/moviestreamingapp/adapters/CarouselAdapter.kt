package com.padcmyanmar.thiha.moviestreamingapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewHolderShowCaseBinding
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewItemBannerBinding
import com.padcmyanmar.thiha.moviestreamingapp.utils.IMAGE_BASE_URL


class CarouselAdapter : CarouselAdapter() {
    private var mCarouselViewHolder: CarouselAdapter.CarouselViewHolder?=null

    private var mShowCaseList: List<MovieVO> = listOf()
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        if (mShowCaseList.isNotEmpty()){

            mCarouselViewHolder=holder
            (mCarouselViewHolder as ShowCaseViewHolder).bindData(mShowCaseList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ViewItemBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShowCaseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(mShowCaseList.count()>5)
            5
        else
            mShowCaseList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(showCaseList:List<MovieVO>){
        mShowCaseList=showCaseList
        notifyDataSetChanged()
    }

    inner class ShowCaseViewHolder(val binding : ViewItemBannerBinding): CarouselViewHolder(binding.root) {


        fun bindData(movie: MovieVO) {
            Glide.with(itemView.context)
                .load("$IMAGE_BASE_URL${movie.posterPath}")
                .into(binding.ivBannerImage)

            binding.tvBannerMovieName.text = movie.title
            }

        }

    }