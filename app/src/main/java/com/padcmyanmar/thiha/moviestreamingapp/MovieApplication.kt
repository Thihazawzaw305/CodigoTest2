package com.padcmyanmar.thiha.moviestreamingapp

import android.app.Application
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModelImpl

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)
    }
}