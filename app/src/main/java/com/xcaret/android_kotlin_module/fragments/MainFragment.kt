package com.xcaret.android_kotlin_module.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.xcaret.android_kotlin_module.R
import com.xcaret.android_kotlin_module.adapters.MoviesAdapter
import com.xcaret.android_kotlin_module.base.BaseFragment
import com.xcaret.android_kotlin_module.databinding.FragmentMainBinding
import com.xcaret.android_kotlin_module.viewmodels.MoviesViewModel

class MainFragment : BaseFragment() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun setContentView(container: ViewGroup?): View =
        FragmentMainBinding.inflate(layoutInflater, container, false).apply {
            viewmodel = moviesViewModel.apply {
                isRefreshing.observe(viewLifecycleOwner) {
                    swipeRefreshMovies.isRefreshing = it
                }
            }
            adapter = MoviesAdapter()
            lifecycleOwner = viewLifecycleOwner
            binding = this
        }.root

    override fun onViewFragmentCreated(view: View, savedInstanceState: Bundle?) {
        showBackIconOnToolbar()
        setHasOptionsMenu(true)
        setTitle(getString(R.string.movies_list))
        binding.swipeRefreshMovies.setOnRefreshListener {
            if (binding.swipeRefreshMovies.isRefreshing) {
                moviesViewModel.getMovies(true)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.action_menu_sort_by_name -> moviesViewModel.sortMoviesByName()
            R.id.action_menu_sort_by_popularity -> moviesViewModel.sortMoviesByPopularity()
            else -> moviesViewModel.sortMoviesByRating()
        }
        return super.onOptionsItemSelected(item)
    }
}