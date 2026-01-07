package com.xcaret.android_kotlin_module.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xcaret.android_kotlin_module.R
import com.xcaret.android_kotlin_module.adapters.MoviesAdapter
import com.xcaret.android_kotlin_module.models.Movie

@BindingAdapter("app:movieItems")
fun setItems(recycler: RecyclerView, items: LiveData<List<Movie>>) {
    recycler.adapter?.let { adapter ->
        if (adapter is MoviesAdapter) {
            adapter.refreshDataMovies(items.value ?: emptyList())

        }
    }
}

@BindingAdapter("app:setAdapter")
fun setAdapter(recycler: RecyclerView, adapter: MoviesAdapter?) {
    adapter?.let {
        recycler.adapter = it
        recycler.layoutManager = GridLayoutManager(recycler.context, 2)
    }
}

@BindingAdapter("android:imageURL")
fun setImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
            .load("https://image.tmdb.org/t/p/w154$it")
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }
}

@BindingAdapter("android:imageHeaderURL")
fun setImageHeader(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
            .load("https://image.tmdb.org/t/p/w500$it")
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }
}