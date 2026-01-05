package com.xcaret.android_kotlin_module.fragments

import android.os.Bundle
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
        setTitle(getString(R.string.movies_list))
        binding.swipeRefreshMovies.setOnRefreshListener {
            if (binding.swipeRefreshMovies.isRefreshing) {
                moviesViewModel.getMovies(true)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}