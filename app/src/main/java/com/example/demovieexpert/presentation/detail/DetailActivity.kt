package com.example.demovieexpert.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.demovieexpert.R
import com.example.demovieexpert.core.data.source.local.entity.MovieEntity
import com.example.demovieexpert.core.data.source.remote.response.ResultsItem
import com.example.demovieexpert.core.domain.model.Movies
import com.example.demovieexpert.databinding.ActivityDetailBinding
import com.example.demovieexpert.core.ui.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val listMovieResponse = intent.getParcelableExtra<Movies>("list_movie")
        binding.apply {
            if ( listMovieResponse != null ){
                tvDetailTitle.text = listMovieResponse.title
                Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/original/${listMovieResponse.poster}").into(ivDetail)
                tvDesc.text = listMovieResponse.overview


                var statusFavorite = listMovieResponse.isFavorite
                setStatusFavorite(statusFavorite)
                binding.fab.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteMovie(listMovieResponse, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }






    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }
}