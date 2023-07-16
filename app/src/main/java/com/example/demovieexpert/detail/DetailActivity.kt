package com.example.demovieexpert.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.demovieexpert.R
import com.example.demovieexpert.core.domain.model.Movies
import com.example.demovieexpert.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Detail Movie"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val listMovieResponse = intent.getParcelableExtra<Movies>("list_movie")
        binding.apply {
            if ( listMovieResponse != null ){
                tvDetailTitle.text = listMovieResponse.title
                Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/original/${listMovieResponse.poster}").into(ivDetail)
                tvDesc.text = listMovieResponse.overview


                var statusFavorite = listMovieResponse.isFavorite
                setStatusFavorite(statusFavorite)
                binding.fab.setOnClickListener {
                    if(statusFavorite) {
                        Toast.makeText(this@DetailActivity, "Removed from Favorite", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@DetailActivity, "Added to Favorite", Toast.LENGTH_SHORT).show()
                    }
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