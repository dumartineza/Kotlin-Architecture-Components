package com.xcaret.android_kotlin_module.interfaces

import com.xcaret.android_kotlin_module.models.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
const val TMDB_API_KEY = "4632e21c3fc6c5d05f09da673b59892c"
interface MoviesAPIService {

    @GET("movie/popular")
    suspend fun getAllMovies(
        @Query("api_key") apiKey: String = TMDB_API_KEY,
        @Query("language") language: String = "es-MX",
        @Query("page") page: Int = 1
    ) : Response<MovieResponse>

    companion object {
        fun create() : MoviesAPIService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TMDB_BASE_URL)
                .client(okHttpClient)
                .build()
            return retrofit.create(MoviesAPIService::class.java)
        }
    }
}