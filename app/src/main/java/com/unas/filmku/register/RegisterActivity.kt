package com.unas.filmku.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.unas.filmku.R
import com.unas.filmku.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener {
            doRegister()
        }
    }

    private fun doRegister() {
        binding.apply {
            var isValid = true

            val firstName = edtFirstName.editText?.text.toString()
            val lastName = edtLastName.editText?.text.toString()
            val email: String = edtEmail.editText?.text.toString()
            val password: String = edtPassword.editText?.text.toString()
            val confirmPass: String = edtConfirmPassword.editText?.text.toString()

            val fullName: String = String.format(
                "%s %s",
                firstName,
                edtLastName.editText?.text.toString()
            )

            if (firstName.isEmpty()) {
                isValid = false
                edtFirstName.setError(getString(R.string.text_please_enter_your_first_name))
            }

            if (lastName.isEmpty()) {
                isValid = false
                edtLastName.setError(getString(R.string.text_please_enter_your_last_name))
            }

            if (email.isEmpty()) {
                isValid = false
                edtEmail.setError(getString(R.string.please_enter_your_email))
            }

            if (password.isEmpty()) {
                isValid = false
                edtPassword.setError(getString(R.string.please_enter_your_password))
            }

            if (confirmPass.isEmpty()) {
                isValid = false
                edtConfirmPassword.setError(getString(R.string.please_enter_your_confirm_password))
            }

            if (confirmPass != password) {
                isValid = false
                edtConfirmPassword.setError(getString(R.string.your_password_not_match))
            }

            if (isValid) {
                registerToFirebase(email, password)
            }
        }
    }

    private fun registerToFirebase(email: String, password: String) {

        firebaseAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnSuccessListener { auth ->

                finish()

            }?.addOnFailureListener { data ->
                data.printStackTrace()
                Toast.makeText(this, "error : ${data.message}", Toast.LENGTH_LONG).show()
            }
    }
}