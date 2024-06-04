package com.unas.filmku.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unas.filmku.databinding.ItemMovieNowShowingBinding
import com.unas.filmku.databinding.ItemMoviePopularBinding
import com.unas.filmku.model.MovieData

class AdapterPopular(
    private val onClickItem : (MovieData) -> Unit
) : RecyclerView.Adapter<AdapterPopular.ViewHolder>() {

    private val dataList = mutableListOf<MovieData>()

    class ViewHolder(
        private val binding : ItemMoviePopularBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovieData, onClickItem : (MovieData) -> Unit) {
            binding.apply {
                tvTitle.text = data.title
                tvRating.text = data.rating
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviePopularBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data, onClickItem)
    }

    fun setData(data : List<MovieData>) {
        dataList.addAll(data)
        notifyDataSetChanged()
    }

}