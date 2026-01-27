package com.xcaret.android_kotlin_module.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xcaret.android_kotlin_module.R
import com.xcaret.android_kotlin_module.base.BaseFragment
import com.xcaret.android_kotlin_module.databinding.ItemMoviesListBinding
import com.xcaret.android_kotlin_module.models.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    private var movieItems = ArrayList<Movie>()
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviesListBinding.inflate(inflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.titleMovie.text = movieItems[position].title
        context?.let {

            Glide.with(it)
                .load("https://image.tmdb.org/t/p/w500${movieItems[position].poster_image_path}")
                .into(holder.posterMovie)

            // Setting the popularity of the movie
            holder.popularityMovie.text =
                it.getString(R.string.popularity_text, movieItems[position].popularity)

            // Setting the rating of the movie
            holder.ratingMovie.text = context!!
                .getString(R.string.rating_text, movieItems[position].rating)
        }

        holder.parent.setOnClickListener {
            val bundle = bundleOf(BaseFragment.HAS_TOOLBAR_KEY to false, "id" to movieItems[position].id)
        }
    }

    override fun getItemCount(): Int = movieItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun refreshDataMovies(list: List<Movie>) {
        movieItems = ArrayList(list)
        notifyDataSetChanged()
    }

    class MovieHolder(binding: ItemMoviesListBinding) : RecyclerView.ViewHolder(binding.root) {
        val parent = binding.root
        val posterMovie: ImageView = binding.imagePosterItem
        val titleMovie: TextView = binding.textTitleMovie
        val popularityMovie: TextView = binding.textPopularity
        val ratingMovie: TextView = binding.textRating
    }
}