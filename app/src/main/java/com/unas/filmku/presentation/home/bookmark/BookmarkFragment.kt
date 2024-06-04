package com.unas.filmku.presentation.home.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.unas.filmku.R
import com.unas.filmku.databinding.FragmentBookmarkBinding
import com.unas.filmku.databinding.FragmentHomeBinding
import com.unas.filmku.model.MovieData
import com.unas.filmku.presentation.home.adapter.AdapterPopular


class BookmarkFragment : Fragment() {

    private lateinit var _binding : FragmentBookmarkBinding
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterPopular = AdapterPopular(::onClickItem)
        binding.rvBookmark.adapter = adapterPopular

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
         * @return A new instance of fragment BookmarkFragment.
         */
        @JvmStatic
        fun newInstance() = BookmarkFragment()
    }
}