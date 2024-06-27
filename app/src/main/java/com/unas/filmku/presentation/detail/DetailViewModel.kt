package com.unas.filmku.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unas.filmku.domain.model.DetailMovieDomain
import com.unas.filmku.domain.model.MovieData
import com.unas.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _detailDataMovie = MutableLiveData<DetailMovieDomain>()
    val detailDataMovie : LiveData<DetailMovieDomain> = _detailDataMovie

    fun getDataMovie(id : Int) {
        viewModelScope.launch {
            repository.getMovieDetail(id).collect {
                _detailDataMovie.value = it
            }
        }
    }


    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess : LiveData<Boolean> = _isSuccess

    fun addToDatabase(data : DetailMovieDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovieToDatabase(data).collect {
                _isSuccess.postValue(it)
            }
        }
    }

}