package com.unas.filmku.presentation.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.data.repository.RepositoryImpl
import com.unas.filmku.presentation.home.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _stateEmailError = MutableLiveData<String?>(null)
    val stateEmailError : LiveData<String?> = _stateEmailError

    private val _statePasswordError = MutableLiveData<String?>(null)
    val statePasswordError : LiveData<String?> = _statePasswordError

    fun doLogin(email: String, password: String) {

        if (email.isBlank()) {
            _stateEmailError.value = "Email tidak boleh kosong"
        }

        if (password.isBlank()) {
            _statePasswordError.value = "Password tidak boleh kosong"
        }

        if (password.isNotEmpty() && email.isNotEmpty()) {
            doLoginFirebase(email, password)
        }
    }

    private val _successLogin = MutableLiveData<Boolean?>(null)
    val successLogin : LiveData<Boolean?> = _successLogin

    private fun doLoginFirebase(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Action Login Success
                _successLogin.value = true

            }.addOnFailureListener { data ->
                data.printStackTrace()
                // Action Login Failed
                _successLogin.value = false
            }
    }
}