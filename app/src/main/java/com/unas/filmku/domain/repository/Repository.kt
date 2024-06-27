package com.unas.filmku.domain.repository

import com.unas.filmku.domain.model.DetailMovieDomain
import com.unas.filmku.domain.model.MovieData
import com.unas.filmku.domain.request.RequestRegister
import kotlinx.coroutines.flow.Flow

interface Repository {

    val isLogin : Boolean

    fun postLogin(email : String, password : String, callback:(Boolean)->Unit)

    fun postRegister(requestRegister: RequestRegister, callback:(Boolean)->Unit)

    fun getMovieShowing() : Flow<List<MovieData>>

    fun getMoviePopular() : Flow<List<MovieData>>

    fun getMovieDetail(id : Int) : Flow<DetailMovieDomain>

    fun getAllMovieBookmark() : Flow<List<MovieData>>

    fun addMovieToDatabase(data : DetailMovieDomain) : Flow<Boolean>

}