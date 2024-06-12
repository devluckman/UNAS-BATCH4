package com.unas.filmku.presentation.home.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unas.filmku.model.MovieData

class HomeViewModel : ViewModel() {

    private val _movieShowing = MutableLiveData<List<MovieData>>(null)
    val movieShowing : LiveData<List<MovieData>> = _movieShowing


    private val _moviePopular = MutableLiveData<List<MovieData>>(null)
    val moviePopular : LiveData<List<MovieData>> = _moviePopular


    fun getDataMovieShowing() {
        // TODO Call API from Repository
        _movieShowing.value = MovieData.dummy
    }

    fun getDataMoviePopular() {
        // TODO Call API from Repository
        _moviePopular.value = MovieData.dummy
    }
}