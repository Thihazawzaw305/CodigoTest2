package com.padcmyanmar.thiha.moviestreamingapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface BasePresenter {
    fun onUiReady(owner : LifecycleOwner)
}