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
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritedActivity : AppCompatActivity() {
    private val favouriteVM: FavouritedViewModel by viewModel()
    private var _binding: ActivityFavouritedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavouritedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvFav.layoutManager = layoutManager


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