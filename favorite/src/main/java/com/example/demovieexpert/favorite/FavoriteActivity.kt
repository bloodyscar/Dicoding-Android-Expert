package com.example.demovieexpert.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.demovieexpert.core.ui.ListFavAdapter
import com.example.demovieexpert.favorite.databinding.ActivityFavoriteBinding
import com.example.demovieexpert.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favFM: FavoriteViewModel by viewModel()
    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Favorite Movie"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvFavModule.layoutManager = layoutManager
        favFM.getAllFavMovie.observe(this) { result ->
            if ( result.isNotEmpty() ){
                val adapter = ListFavAdapter(result, this@FavoriteActivity)
                binding.rvFavModule.adapter = adapter
            } else {
                Toast.makeText(this@FavoriteActivity, "Empty Favorite", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}