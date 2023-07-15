package com.example.demovieexpert.presentation.favourited

import FavouritedViewModel
import com.example.demovieexpert.core.ui.ListFavAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.demovieexpert.databinding.ActivityFavouritedBinding
import com.example.demovieexpert.core.ui.ViewModelFactory

class FavouritedActivity : AppCompatActivity() {
    private lateinit var favouriteVM: FavouritedViewModel
    private var _binding: ActivityFavouritedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavouritedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvFav.layoutManager = layoutManager

        val factory = ViewModelFactory.getInstance(this)
        favouriteVM = ViewModelProvider(this, factory)[FavouritedViewModel::class.java]

        favouriteVM.getAllFav.observe(this) { result ->
            if ( result.isNotEmpty() ){
                val adapter = ListFavAdapter(result, this@FavouritedActivity)
                binding.rvFav.adapter = adapter
            } else {
                Toast.makeText(this@FavouritedActivity, "Empty Favorite", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}