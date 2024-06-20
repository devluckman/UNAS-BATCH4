package com.unas.filmku.presentation.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.unas.filmku.databinding.ActivityRegisterBinding
import com.unas.filmku.domain.request.RequestRegister
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        viewModel.successRegister.observe(this) { isSuccess ->
            if (isSuccess != null) {
                if (isSuccess) {

                    // Register Success back to login
                    finish()

                } else {
                    Toast.makeText(this, "Register Gagal", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            doRegister()
        }

    }

    private fun doRegister() {
        binding.apply {
            val requestRegister = RequestRegister(
                firstName = edtFirstName.editText?.text.toString(),
                lastName = edtLastName.editText?.text.toString(),
                email = edtEmail.editText?.text.toString(),
                password = edtPassword.editText?.text.toString(),
                confirmPass = edtConfirmPassword.editText?.text.toString()
            )

            viewModel.doRegister(requestRegister)
        }
    }
}