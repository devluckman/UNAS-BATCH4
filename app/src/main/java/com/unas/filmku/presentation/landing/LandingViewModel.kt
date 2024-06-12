package com.unas.filmku.presentation.landing

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LandingViewModel : ViewModel() {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _isLogin = MutableLiveData<Boolean?>(null)
    val isLogin : LiveData<Boolean?> = _isLogin

    private fun checkLogin() {
        Handler(Looper.getMainLooper()).postDelayed({

            _isLogin.value = firebaseAuth.currentUser != null

        }, 2000)
    }


    init {
        checkLogin()
    }

}