package com.padcmyanmar.thiha.moviestreamingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewHolderActorBinding
import com.padcmyanmar.thiha.moviestreamingapp.utils.IMAGE_BASE_URL


class ActorViewHolder(private val binding: ViewHolderActorBinding):RecyclerView.ViewHolder(binding.root) {

    fun bindData(actor : ActorVO){

        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${actor.profilePath}")
            .into(binding.ivActorImage)
        binding.tvActorName.text = actor.name
    }
}