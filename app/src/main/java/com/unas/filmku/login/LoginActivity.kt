package com.unas.filmku.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.R
import com.unas.filmku.databinding.ActivityLoginBinding
import com.unas.filmku.home.MainActivity
import com.unas.filmku.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            doLogin()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun doLogin() {
        val email = binding.edtEmail.editText?.text.toString()
        val password = binding.edtPassword.editText?.text.toString()

        if (email.isBlank()) {
            binding.edtEmail.setError("Email Tidak boleh kosong")
        }

        if (password.isBlank()) {
            binding.edtPassword.setError("Password Tidak boleh kosong")
        }

        if (password.isNotEmpty() && email.isNotEmpty()) {
            doLoginFirebase(email, password)
        }
    }

    private fun doLoginFirebase(email: String, password: String) {
        firebaseAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnSuccessListener {

                Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }?.addOnFailureListener { data ->
                data.printStackTrace()
                Toast.makeText(this, "${data.message}", Toast.LENGTH_LONG).show()

            }
    }

}