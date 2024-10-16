package com.example.demo2.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo2.R
import com.example.demo2.Util.EndlessScrollListener
import com.example.demo2.adapter.AdapterMovies
import com.example.demo2.databinding.ActivityMainBinding
import com.example.demo2.model.Post
import com.example.demo2.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var movieAdapter: AdapterMovies
    private var currentPage = 0
    private val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.recycleView.addOnScrollListener(object : EndlessScrollListener() {
            override fun onLoadMore(page: Int) {
                Log.d(TAG,"onLoadMore")
                viewModel.requestFirstPageTopMovies(page)
            }
        })

        viewModel.requestFirstPageTopMovies(currentPage)
        viewModel.topMoviesFirstPageResponse.observe(this, Observer { movieList ->
            val mutablePosts: MutableList<Post> =
                movieList?.posts?.toMutableList() ?: mutableListOf()
            Log.d("MutablePostsList", "List of posts: $mutablePosts")

            movieAdapter.setMovieList(mutablePosts)
        })
    }

    private fun prepareRecyclerView() {
        movieAdapter = AdapterMovies()
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
        }
    }



}