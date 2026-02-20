package com.example.android_kotlin_module.network

import android.os.Build
import com.xcaret.android_kotlin_module.repositories.MoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class APITest {

    private val client = MockApiService()
    private val repository = MoviesRepository(client.apiService)

    @Before
    fun setup() {
        client.startMockServer()
    }

    @Test
    fun checkNumberElementsInMovies() = runBlocking {
        val result = repository.getMoviesFromAPI()
        val movies = result?.body()
        assert(movies?.results?.isNotEmpty() == true)
    }

    @After
    fun  tearDown(){
        client.stopMockServer()
    }


}