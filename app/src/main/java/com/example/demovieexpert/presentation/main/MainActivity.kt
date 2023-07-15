package com.example.demovieexpert.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.demovieexpert.R
import com.example.demovieexpert.core.data.Resource
import com.example.demovieexpert.databinding.ActivityMainBinding
import com.example.demovieexpert.core.ui.ViewModelFactory
import com.example.demovieexpert.core.ui.ListMovieAdapter
import com.example.demovieexpert.presentation.favourited.FavouritedActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvMain.layoutManager = layoutManager

        val factory = ViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        mainViewModel.movie.observe(this) { result ->
            if ( result != null ){
                when(result){
                    is Resource.Loading -> {
                        Log.d("FAc2132SD", "Loading")
                    }
                    is Resource.Success -> {
                        Log.d("FAc2132SD", result.data.toString())
                        val adapter = ListMovieAdapter(result.data!!, this@MainActivity)
                        binding.rvMain.adapter = adapter
                    }
                    is Resource.Error -> {
                        Log.d("FAc2132SD", "Error")
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav -> {
                val intent = Intent(this, FavouritedActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}