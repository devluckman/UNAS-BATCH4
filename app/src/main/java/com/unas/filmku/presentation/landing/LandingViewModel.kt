package com.unas.filmku.presentation.landing

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _isLogin = MutableLiveData<Boolean?>(null)
    val isLogin : LiveData<Boolean?> = _isLogin

    private fun checkLogin() {
        Handler(Looper.getMainLooper()).postDelayed({

            _isLogin.value = repository.isLogin

        }, 2000)
    }


    init {
        checkLogin()
    }

}