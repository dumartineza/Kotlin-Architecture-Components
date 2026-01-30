package com.xcaret.android_kotlin_module.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xcaret.android_kotlin_module.interfaces.MoviesAPIService
import com.xcaret.android_kotlin_module.models.Movie
import com.xcaret.android_kotlin_module.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    private val TAG = MoviesViewModel::class.java.simpleName
    private lateinit var movies: MutableLiveData<List<Movie>>
    var isRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getMovies()
    }

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
        val call = MoviesAPIService.create().getAllMovies()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "Something went wrong, $t")
                isRefreshing.postValue(false)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val listMovies = response.body()!!.results
                    Log.d(TAG, listMovies.toString())
                    movies.postValue(listMovies)
                } else {
                    Log.e(TAG, "Error occurred: ${response.message()}")
                }
                isRefreshing.postValue(false)
            }
        })
    }

}