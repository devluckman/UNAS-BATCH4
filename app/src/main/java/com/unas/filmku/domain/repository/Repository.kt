package com.unas.filmku.domain.repository

import com.unas.filmku.domain.request.RequestRegister

interface Repository {

    val isLogin : Boolean

    fun postLogin(email : String, password : String)

    fun postRegister(requestRegister: RequestRegister)

    fun getMovieShowing()

    fun getMoviePopular()


}