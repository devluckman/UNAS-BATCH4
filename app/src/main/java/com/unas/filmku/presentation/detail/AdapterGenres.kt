package com.unas.filmku.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unas.filmku.databinding.ItemGenresBinding

class AdapterGenres : RecyclerView.Adapter<AdapterGenres.ViewHolder>() {

    private val dataList = mutableListOf<String>()

    class ViewHolder(
        private val binding : ItemGenresBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String) {
            binding.tvGenre.text = data
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenresBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    fun setData(data : List<String>) {
        dataList.addAll(data)
        notifyDataSetChanged()
    }

}