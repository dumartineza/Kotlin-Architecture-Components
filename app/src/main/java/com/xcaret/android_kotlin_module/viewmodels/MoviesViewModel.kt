package com.xcaret.android_kotlin_module.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xcaret.android_kotlin_module.models.Movie
import com.xcaret.android_kotlin_module.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val TAG = MoviesViewModel::class.java.simpleName
    private lateinit var movies: MutableLiveData<List<Movie>>
    private val repository = MoviesRepository()
    var isRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    @JvmOverloads
    fun getMovies(reloadAgain: Boolean = false): LiveData<List<Movie>> {
        if (!::movies.isInitialized) {
            movies = MutableLiveData()
            getMoviesFromAPI()
        }
        if (reloadAgain) {
            getMoviesFromAPI()
        }
        return movies
    }

    private fun getMoviesFromAPI() {
        isRefreshing.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getMoviesFromAPI()
                if (response?.isSuccessful == true && response.body() != null) {
                    movies.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "Error occurred: ${response?.message()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Something went wrong, $e")
            }
            isRefreshing.postValue(false)
        }
    }
}