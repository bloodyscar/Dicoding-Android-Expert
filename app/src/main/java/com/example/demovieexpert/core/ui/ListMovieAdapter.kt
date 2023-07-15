package com.example.demovieexpert.core.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demovieexpert.core.data.source.remote.response.ResultsItem
import com.example.demovieexpert.core.domain.model.Movies
import com.example.demovieexpert.databinding.ItemRvBinding
import com.example.demovieexpert.presentation.detail.DetailActivity

class ListMovieAdapter(private val listMovie: List<Movies>, private var ctx: Context) :
    RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {

    class ListViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movies){
            binding.apply {
                tvTitle.text = movie.title
                Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original/${movie.poster}").into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listMovie[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            val detail = Intent(ctx, DetailActivity::class.java)
            detail.putExtra("list_movie", listMovie[position])
            ctx.startActivity(detail)
        }
    }
}