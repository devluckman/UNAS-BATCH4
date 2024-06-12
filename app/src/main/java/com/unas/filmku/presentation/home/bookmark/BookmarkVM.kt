package com.unas.filmku.presentation.home.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unas.filmku.domain.model.MovieData

class BookmarkVM : ViewModel() {

    private val _movieBookmark = MutableLiveData<List<MovieData>>(null)
    val movieBookmark: LiveData<List<MovieData>> = _movieBookmark


    fun getMovieBookmark() {
        // TODO Get data Database from Repository
        _movieBookmark.value = MovieData.dummy
    }
}