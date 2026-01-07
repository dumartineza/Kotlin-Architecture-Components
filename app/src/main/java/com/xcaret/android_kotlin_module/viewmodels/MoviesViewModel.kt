package com.xcaret.android_kotlin_module.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xcaret.android_kotlin_module.database.MoviesDatabase
import com.xcaret.android_kotlin_module.models.Movie
import com.xcaret.android_kotlin_module.repositories.MoviesRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MoviesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private lateinit var movies: MutableLiveData<List<Movie>>
    var isRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getMovies()
    }

    private val repository: MoviesRepository by lazy {
        MoviesRepository(
            database = MoviesDatabase.getInstance(application)
        )
    }

    @JvmOverloads
    fun getMovies(reloadAgain: Boolean = false): LiveData<List<Movie>> {
        if (!::movies.isInitialized) {
            movies = MutableLiveData()
            getMoviesFromRepository()
        }
        if (reloadAgain) {
            getMoviesFromRepository()
        }
        return movies
    }

    private fun getMoviesFromRepository() {
        isRefreshing.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val moviesJob: Deferred<List<Movie>> = async {
                repository.getMovies()
            }
            movies.postValue(moviesJob.await())
            isRefreshing.postValue(false)
        }
    }

    fun sortMoviesByName() = movies.postValue(movies.value?.sortedBy { it.title })

    fun sortMoviesByPopularity() = movies.postValue(movies.value?.sortedByDescending { it.popularity })

    fun sortMoviesByRating() = movies.postValue(movies.value?.sortedByDescending { it.rating })
}