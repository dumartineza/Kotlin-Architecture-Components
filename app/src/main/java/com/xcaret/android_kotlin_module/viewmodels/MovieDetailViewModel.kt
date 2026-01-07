package com.xcaret.android_kotlin_module.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xcaret.android_kotlin_module.models.Movie
import com.xcaret.android_kotlin_module.repositories.MoviesRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> = _selectedMovie
    lateinit var repository: MoviesRepository

    fun getMovie(id: Long) = viewModelScope.launch {
        val movie = repository.getMovieBy(id)
        _selectedMovie.postValue(movie)
    }

    fun getRating(rating: Double) = rating.toInt()
}