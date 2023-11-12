package com.padcmyanmar.thiha.moviestreamingapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.adapters.ActorListAdapter
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO


class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {


    lateinit var mActorListAdapter: ActorListAdapter
    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpRecyclerView()
    }

    fun setData(actorList : List<ActorVO>){
        mActorListAdapter.setNewData(actorList)
    }


    fun setUpActorViewPod(backgroundColorReference : Int, titleText : String , moreTitleText : String){
        setBackgroundColor(ContextCompat.getColor(context,backgroundColorReference))
        val tvBestActors = findViewById<TextView>(R.id.tvBestActors)
        val tvMoreActors = findViewById<TextView>(R.id.tvMoreActors)
        tvBestActors.text = titleText
        tvMoreActors.text = moreTitleText

    }

    private fun setUpRecyclerView(){
        mActorListAdapter = ActorListAdapter()
        val rvActorList = findViewById<RecyclerView>(R.id.rvActorList)
        rvActorList.adapter = mActorListAdapter

    }
}