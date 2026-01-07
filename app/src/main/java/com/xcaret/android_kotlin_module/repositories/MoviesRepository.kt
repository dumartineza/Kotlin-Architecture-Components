package com.xcaret.android_kotlin_module.repositories

import android.util.Log
import com.xcaret.android_kotlin_module.database.MoviesDatabase
import com.xcaret.android_kotlin_module.interfaces.MoviesAPIService
import com.xcaret.android_kotlin_module.models.Movie
import com.xcaret.android_kotlin_module.network.Connectivity

class MoviesRepository(private val service: MoviesAPIService? = MoviesAPIService.getInstance(),
                       private val database: MoviesDatabase) {

    suspend fun getMovies(): List<Movie> {
        try {
            return if (Connectivity.isConnected) {
                val response = getMoviesFromAPI()
                if (response?.isSuccessful == true && response.body() != null) {
                    val movieResponse = response.body()
                    val movies = movieResponse?.results ?: emptyList()
                    database.moviesDao().insertMovies(movies)
                    movies
                } else {
                    Log.e(TAG, "Error occurred: ${response?.message()}")
                    emptyList()
                }
            } else {
                getMoviesFromDatabase()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Something went wrong, $e")
        }
        return emptyList()
    }

    private suspend fun getMoviesFromAPI() = service?.getAllMovies()

    private suspend fun getMoviesFromDatabase() = database.moviesDao().loadAllMovies()

    suspend fun getMovieBy(id: Long) = database.moviesDao().getMovieById(id)

    private companion object {
        val TAG: String = MoviesRepository::class.java.simpleName
    }
}