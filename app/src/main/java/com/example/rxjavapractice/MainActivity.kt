package com.example.rxjavapractice

import android.os.Build
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
        requestPopularMovie(true)

        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.hasFixedSize()

        binding.container.setOnRefreshListener {
            requestPopularMovie(false)
            binding.container.isRefreshing = false
        }

        viewModel.getResults().observe(this, Observer {
            movieListAdapter = MovieListAdapter(it.results!!)
            binding.rvMovie.adapter = movieListAdapter
            Handler().postDelayed({
                movieListAdapter.isShimmer = false
                movieListAdapter.notifyDataSetChanged()

                binding.mainShimmer.stopShimmer()
                binding.mainShimmer.hideShimmer()

                binding.totalResult.background = null
                binding.totalResult.text = "Total Result: ${it.total_results}"

            }, 3000)
        })

        viewModel.getError().observe(this, Observer {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
        })

        viewModel.getLoad().observe(this, Observer {
            Log.d("MainActivity", "load observer value $it")
//            if(it) {
//                binding.mainShimmer.startShimmer()
//            }
        })
    }

    private fun requestPopularMovie(first: Boolean) {
        if(first) {
            viewModel.getPopularMovie()
            binding.mainShimmer.startShimmer()
        } else {
            viewModel.getPopularMovie()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.totalResult.background = getDrawable(R.drawable.shimmer_shape)
            }
            binding.totalResult.text = ""
            binding.mainShimmer.showShimmer(true)
            binding.mainShimmer.startShimmer()
        }
    }
}
