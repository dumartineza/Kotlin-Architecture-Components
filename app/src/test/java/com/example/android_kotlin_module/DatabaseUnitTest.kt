package com.example.android_kotlin_module

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.xcaret.android_kotlin_module.database.MovieDAO
import com.xcaret.android_kotlin_module.database.MoviesDatabase
import com.xcaret.android_kotlin_module.models.Movie
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/*
====================================
PRUEBAS UNITARIAS - JVM (test)
====================================
EJECUCIÓN:
- Se ejecutan en la JVM local
- NO requieren emulador ni dispositivo
USO PRINCIPAL:
- Lógica de negocio
- ViewModels
- UseCases
- Repositories (mock)
- DAOs con Room + Robolectric
LIMITACIONES:
- No Android real
- No sensores, GPS, cámara real
- Simulación del framework Android
HERRAMIENTAS:
- JUnit
- Robolectric
- Mockito / MockK
- kotlinx-coroutines-test
VELOCIDAD:
- Muy rápidas
*/

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class DatabaseUnitTest {

    private lateinit var movieDAO: MovieDAO
    private lateinit var database: MoviesDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            MoviesDatabase::class.java
        ).allowMainThreadQueries().build()

        movieDAO = database.moviesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertMovies() = runBlocking {
        val movies = generateMovies()
        movieDAO.insertMovies(movies)

        val moviesFromDB = movieDAO.loadAllMovies()
        assertEquals(movies, moviesFromDB)
    }

    @Test
    fun deleteMovies() = runBlocking {
        val movies = generateMovies()
        movieDAO.insertMovies(movies)

        movieDAO.deleteMovies(movies)
        val moviesFromDB = movieDAO.loadAllMovies()

        assertEquals(0, moviesFromDB.size)
    }

    private fun generateMovies(): List<Movie> = listOf(
        Movie(title = "Movie1", id = 1, description = "Description Movie 1"),
        Movie(title = "Movie2", id = 2, description = "Description Movie 2"),
        Movie(title = "Movie3", id = 3, description = "Description Movie 3")
    )
}