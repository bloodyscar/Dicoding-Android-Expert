package com.example.demovieexpert.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.demovieexpert.R
import com.example.demovieexpert.databinding.ActivityMainBinding
import com.example.demovieexpert.core.ui.ListMovieAdapter
import com.example.demovieexpert.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var listMovieAdapter: ListMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Home Movie"

        listMovieAdapter = ListMovieAdapter()
        listMovieAdapter.onItemClick = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra("list_movie", movie)
                startActivity(it)
            }
        }

        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvMain.layoutManager = layoutManager


        mainViewModel.movie.observe(this) { result ->
            if ( result != null ){
                when(result){
                    is com.example.demovieexpert.core.data.Resource.Loading -> {
                        Log.d("FAc2132SD", "Loading")
                    }
                    is com.example.demovieexpert.core.data.Resource.Success -> {
                        listMovieAdapter.setData(result.data)
                    }
                    is com.example.demovieexpert.core.data.Resource.Error -> {
                        Log.d("FAc2132SD", "Error")
                    }
                }
            }
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        with(binding.rvMain) {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = listMovieAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav -> {
                val uri = Uri.parse("demovieexpert://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }


}