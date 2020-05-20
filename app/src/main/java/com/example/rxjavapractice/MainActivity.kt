package com.example.rxjavapractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavapractice.adapter.MovieListAdapter
import com.example.rxjavapractice.databinding.ActivityMainBinding
import com.example.rxjavapractice.utils.Injection
import com.example.rxjavapractice.viewmodels.MainViewModel
import com.example.rxjavapractice.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var binding: ActivityMainBinding

    private lateinit var movieListAdapter: MovieListAdapter
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelFactory = Injection.proviewMainViewModelFactory()
        viewModel.getPopularMovie()

        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.hasFixedSize()

        binding.container.setOnRefreshListener {
            viewModel.getPopularMovie()
            binding.container.isRefreshing = false
        }

        viewModel.getResults().observe(this, Observer {
            movieListAdapter = MovieListAdapter(it.results!!)
            binding.rvMovie.adapter = movieListAdapter
            Handler().postDelayed({
                movieListAdapter.isShimmer = false
                movieListAdapter.notifyDataSetChanged()
            }, 3000)
        })

        viewModel.getError().observe(this, Observer {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
        })

        viewModel.getLoad().observe(this, Observer {
            Log.d("MainActivity", "load observer value $it")
        })
    }
}
