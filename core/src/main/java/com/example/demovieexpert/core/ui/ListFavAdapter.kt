package com.example.demovieexpert.core.ui
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demovieexpert.core.databinding.ItemFavouritedBinding
import com.example.demovieexpert.core.domain.model.Movies
class ListFavAdapter(private val listMovie: List<Movies>, private var ctx: Context) :
    RecyclerView.Adapter<ListFavAdapter.ListViewHolder>() {

    class ListViewHolder(private val binding: ItemFavouritedBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movies){
            binding.apply {
                tvFavTitle.text = movie.title
                Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original/${movie.poster}").into(ivFav)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemFavouritedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listMovie[position]
        holder.bind(user)
    }
}