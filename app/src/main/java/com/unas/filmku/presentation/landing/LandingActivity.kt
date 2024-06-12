package com.unas.filmku.presentation.landing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.R
import com.unas.filmku.presentation.home.MainActivity
import com.unas.filmku.presentation.login.LoginActivity

class LandingActivity : AppCompatActivity() {

    val viewModel: LandingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing)

        viewModel.isLogin.observe(this) { isLogin ->
            if (isLogin != null) {
                if (isLogin) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }

    }
}