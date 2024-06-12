package com.unas.filmku.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.R
import com.unas.filmku.databinding.ActivityLoginBinding
import com.unas.filmku.presentation.home.MainActivity
import com.unas.filmku.presentation.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            doLogin()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }


        viewModel.stateEmailError.observe(this) { message ->
            if (message != null) {
                binding.edtEmail.setError(message)
            }
        }

        viewModel.statePasswordError.observe(this) { message ->
            if (message != null) {
                binding.edtPassword.setError(message)
            }
        }

        viewModel.successLogin.observe(this) { isSuccess ->
            if (isSuccess != null) {
                if (isSuccess) {

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(this, "Login Gagal", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun doLogin() {
        val email = binding.edtEmail.editText?.text.toString()
        val password = binding.edtPassword.editText?.text.toString()

        viewModel.doLogin(
            email = email,
            password = password
        )
    }

}