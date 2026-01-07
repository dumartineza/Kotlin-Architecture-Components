package com.xcaret.android_kotlin_module.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xcaret.android_kotlin_module.models.Movie

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movies")
    suspend fun loadAllMovies(): List<Movie>

    @Query("SELECT * FROM Movies WHERE id = :idMovie")
    suspend fun getMovieById(idMovie: Long): Movie

    @Delete
    suspend fun deleteMovies(movies: List<Movie>)
}