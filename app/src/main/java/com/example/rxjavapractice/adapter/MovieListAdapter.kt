package com.example.rxjavapractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavapractice.R
import com.example.rxjavapractice.models.Movie
import com.example.rxjavapractice.models.behavior.Movie.getOverview
import com.facebook.shimmer.ShimmerFrameLayout

class MovieListAdapter(private var items: List<Movie>): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var isShimmer = true
    private var shimmerNumber = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if(isShimmer) {
            shimmerNumber
        } else {
            items.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(isShimmer) {
            holder.shimmer.startShimmer()
        } else {
            holder.shimmer.stopShimmer()
            holder.shimmer.setShimmer(null)

            holder.onBind(items[position])
        }
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.movie_title)
        private val description: TextView = view.findViewById(R.id.movie_description)
        private val poster: ImageView = view.findViewById(R.id.movie_poster)
        val shimmer: ShimmerFrameLayout = view.findViewById(R.id.shimmer)

        fun onBind(movie: Movie) {
            title.background = null
            description.background = null
            poster.background = null

            Glide.with(view.context).load("http://image.tmdb.org/t/p/w185${movie.poster_path}").into(poster)
            title.text = movie.title.toString()
            description.text = getOverview(movie.overview.toString())
        }

    }

}