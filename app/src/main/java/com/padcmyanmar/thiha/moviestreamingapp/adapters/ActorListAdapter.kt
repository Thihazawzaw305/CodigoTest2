package com.padcmyanmar.thiha.moviestreamingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ViewHolderActorBinding
import com.padcmyanmar.thiha.moviestreamingapp.viewholders.ActorViewHolder

class ActorListAdapter:RecyclerView.Adapter<ActorViewHolder>() {

    private var mActorList : List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = ViewHolderActorBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
if (mActorList.isNotEmpty()){
    holder.bindData(mActorList[position])
}
    }

    override fun getItemCount(): Int {
      return mActorList.count()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(actorlist : List<ActorVO>){
        mActorList = actorlist
        notifyDataSetChanged()
    }
}