package com.unas.filmku.presentation.landing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.R
import com.unas.filmku.presentation.home.MainActivity
import com.unas.filmku.presentation.login.LoginActivity

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing)

        val firebaseAuth = FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed({

            if (firebaseAuth.currentUser == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }

        }, 2000)

    }
}