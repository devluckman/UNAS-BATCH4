package com.unas.filmku.presentation.home.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.unas.filmku.R
import com.unas.filmku.databinding.FragmentHomeBinding
import com.unas.filmku.model.MovieData
import com.unas.filmku.presentation.home.adapter.AdapterNowShowing
import com.unas.filmku.presentation.home.adapter.AdapterPopular

class HomeFragment : Fragment() {

    private lateinit var _binding : FragmentHomeBinding
    val binding get() = _binding

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

        adapter.setData(MovieData.dummy)
        adapterPopular.setData(MovieData.dummy)
    }

    private fun onClickItem(data : MovieData) {
        Toast.makeText(context, "Movies ${data.title}", Toast.LENGTH_LONG).show()
        // TODO Action to Page Detail

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