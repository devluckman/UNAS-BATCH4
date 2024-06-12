package com.unas.filmku.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.domain.repository.Repository
import com.unas.filmku.domain.request.RequestRegister

class RepositoryImpl(
    private val database : Any,
    private val api : Any,
    private val firebaseAuth: FirebaseAuth
) : Repository {

    override val isLogin: Boolean
        get() = TODO("Not yet implemented")

    override fun postLogin(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun postRegister(requestRegister: RequestRegister) {
        TODO("Not yet implemented")
    }

    override fun getMovieShowing() {
        TODO("Not yet implemented")
    }

    override fun getMoviePopular() {
        TODO("Not yet implemented")
    }

}