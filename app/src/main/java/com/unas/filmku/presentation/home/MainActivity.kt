package com.unas.filmku.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.R
import com.unas.filmku.databinding.ActivityMainBinding
import com.unas.filmku.presentation.home.bookmark.BookmarkFragment
import com.unas.filmku.presentation.home.home.HomeFragment
import com.unas.filmku.presentation.landing.LandingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonLogout.setOnClickListener {

            firebaseAuth.signOut()
            startActivity(Intent(this, LandingActivity::class.java))

        }

        binding.btnHome.setOnClickListener {
            inflateFragment(HomeFragment.newInstance())
        }

        binding.btnBookmark.setOnClickListener {
            inflateFragment(BookmarkFragment.newInstance())
        }

        inflateFragment(HomeFragment.newInstance())
    }


    private fun inflateFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}