package com.xcaret.android_kotlin_module.repositories

import com.xcaret.android_kotlin_module.interfaces.MoviesAPIService

class MoviesRepository(private val service: MoviesAPIService? = MoviesAPIService.create()) {
    suspend fun getMoviesFromAPI() = service?.getAllMovies()
}