package com.unas.filmku.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.unas.filmku.R
import com.unas.filmku.databinding.ActivityDetailBinding
import com.unas.filmku.domain.model.DetailMovieDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.detailDataMovie.observe(this) {
            setDataView(it)
        }

        viewModel.isSuccess.observe(this) {
            val message = if (it) {
                "Berhasil Insert Data ke Database"
            } else {
                "Gagal Insert data"
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        val id = intent.getIntExtra(KEY_ID, 0)
        viewModel.getDataMovie(id)
    }

    private fun setDataView(data: DetailMovieDomain) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(data.image)
                .into(ivImage)

            tvTitle.text = data.title
            tvRating.text = data.rating
            tvDuration.text = data.duration
            tvDescription.text = data.description
            val adapter = AdapterGenres()
            rvGenres.adapter = adapter
            adapter.setData(data.genres)

            ivBookmark.setOnClickListener {
                viewModel.addToDatabase(data)
            }
        }
    }


    companion object {
        const val KEY_ID = "KEY_ID"
        fun newInstance(context: Context?, id: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(KEY_ID, id)
            context?.startActivity(intent)
        }

    }
}