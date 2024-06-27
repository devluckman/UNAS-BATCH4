package com.unas.filmku.presentation.home.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.unas.filmku.databinding.FragmentHomeBinding
import com.unas.filmku.domain.model.MovieData
import com.unas.filmku.presentation.detail.DetailActivity
import com.unas.filmku.presentation.home.adapter.AdapterNowShowing
import com.unas.filmku.presentation.home.adapter.AdapterPopular
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding : FragmentHomeBinding
    val binding get() = _binding

    val viewModel : HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // Reference https://developer.android.com/topic/libraries/view-binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = AdapterNowShowing(::onClickItem)
        binding.rvNowShowing.adapter = adapter

        val adapterPopular = AdapterPopular(::onClickItem)
        binding.rvPopular.adapter = adapterPopular

        viewModel.movieShowing.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.moviePopular.observe(viewLifecycleOwner) {
            adapterPopular.setData(it)
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            fetchData()
        }

        fetchData()
    }

    private fun fetchData() {
        viewModel.getDataMoviePopular()
        viewModel.getDataMovieShowing()
    }

    private fun onClickItem(data : MovieData) {
        Toast.makeText(context, "Movies ${data.title}", Toast.LENGTH_LONG).show()

        startActivity(Intent(context, DetailActivity::class.java))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}