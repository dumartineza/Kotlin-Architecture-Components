package com.xcaret.android_kotlin_module.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.xcaret.android_kotlin_module.base.BaseFragment
import com.xcaret.android_kotlin_module.database.MoviesDatabase
import com.xcaret.android_kotlin_module.databinding.FragmentDetailMovieBinding
import com.xcaret.android_kotlin_module.repositories.MoviesRepository
import com.xcaret.android_kotlin_module.viewmodels.MovieDetailViewModel

class DetailMovieFragment : BaseFragment() {

    private val detailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailMovieBinding
    private var idMovie: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idMovie = arguments?.getLong("id") ?: 0L
    }

    override fun setContentView(container: ViewGroup?): View =
        FragmentDetailMovieBinding.inflate(LayoutInflater.from(requireContext()), container, false).apply {
            viewmodel = detailViewModel.apply {
                repository = MoviesRepository(database = MoviesDatabase.getInstance(requireContext()))
                getMovie(idMovie)
            }
            ratingBar.max = 10
            (requireActivity() as AppCompatActivity).apply {
                this.setSupportActionBar(animToolbar)
                this.title = "Detail screen"
                this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            lifecycleOwner = viewLifecycleOwner
            binding = this
        }.root

    override fun onViewFragmentCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}