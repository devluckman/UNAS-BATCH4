package com.unas.filmku.presentation.home.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unas.filmku.domain.model.MovieData
import com.unas.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _movieShowing = MutableLiveData<List<MovieData>>(emptyList())
    val movieShowing : LiveData<List<MovieData>> = _movieShowing


    private val _moviePopular = MutableLiveData<List<MovieData>>(emptyList())
    val moviePopular : LiveData<List<MovieData>> = _moviePopular

    fun getDataMovieShowing() {
        viewModelScope.launch {
            repository.getMovieShowing()
                .collect {
                    _movieShowing.value = it
                }
        }
    }

    fun getDataMoviePopular() {
        viewModelScope.launch {
            repository.getMoviePopular().collect {
                _moviePopular.value = it
            }
        }
    }
}