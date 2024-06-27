package com.unas.filmku.presentation.home.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unas.filmku.domain.model.MovieData
import com.unas.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkVM @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _movieBookmark = MutableLiveData<List<MovieData>>(emptyList())
    val movieBookmark: LiveData<List<MovieData>> = _movieBookmark


    fun getMovieBookmark() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovieBookmark().collect {
                _movieBookmark.postValue(it)
            }
        }
    }
}