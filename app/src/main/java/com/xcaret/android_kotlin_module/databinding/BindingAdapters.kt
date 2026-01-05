package com.xcaret.android_kotlin_module.databinding

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xcaret.android_kotlin_module.adapters.MoviesAdapter
import com.xcaret.android_kotlin_module.models.Movie

@BindingAdapter("app:movieItems")
fun setItems(recycler: RecyclerView, items: LiveData<List<Movie>>) {
    recycler.adapter?.let { adapter ->
        if (adapter is MoviesAdapter) {
            items.observe(recycler.context as AppCompatActivity) {
                adapter.refreshDataMovies(it)
            }
        }
    }
}

@BindingAdapter("app:setAdapter")
fun setAdapter(recycler: RecyclerView, adapter: MoviesAdapter?) {
    adapter?.let {
        recycler.adapter = it
        recycler.layoutManager = GridLayoutManager(recycler.context as AppCompatActivity, 2)
    }
}