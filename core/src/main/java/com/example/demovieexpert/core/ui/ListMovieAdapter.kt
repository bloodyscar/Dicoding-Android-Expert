package com.example.demovieexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demovieexpert.core.domain.model.Movies
import com.example.demovieexpert.core.databinding.ItemRvBinding

class ListMovieAdapter : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {

    private var listMovie = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return
        listMovie.clear()
        listMovie.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            binding.apply {
                tvTitle.text = movie.title
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${movie.poster}").into(imageView)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
//        holder.itemView.setOnClickListener {
//            val detail = Intent(ctx, DetailActivity::class.java)
//            detail.putExtra("list_movie", listMovie[position])
//            ctx.startActivity(detail)
//        }
    }
}