package com.example.android_kotlin_module.network

import com.xcaret.android_kotlin_module.interfaces.MoviesAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class MockApiService {

    private val mockServer by lazy {
        MockWebServer().apply {
            dispatcher = mockDispatcher
        }
    }

    val apiService: MoviesAPIService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockServer.url("/"))
            .client(okHttpClient)
            .build()
            .create(MoviesAPIService::class.java)
    }

    private val mockDispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when {
                request.path?.startsWith("/movie/popular") == true ->{
                    MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody(readContentFromFilePath("json/movies.json"))
                }

                else -> MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)

            }
        }
    }

    fun startMockServer() = mockServer.start()
    fun stopMockServer() = mockServer.shutdown()

    fun readContentFromFilePath(path:String): String{
        return javaClass.classLoader!!
            .getResourceAsStream(path)
            ?.bufferedReader()
            ?.use { it.readText()}
            ?:throw IllegalArgumentException("File not foud:$path")
    }




}