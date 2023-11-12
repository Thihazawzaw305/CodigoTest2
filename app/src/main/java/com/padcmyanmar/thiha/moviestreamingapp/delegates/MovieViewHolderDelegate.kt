package com.padcmyanmar.thiha.moviestreamingapp.delegates

interface MovieViewHolderDelegate {
    fun onTapMovie(movieId : Int)
    fun toggleFavourite(id: Int, isFavourite: Boolean)
}