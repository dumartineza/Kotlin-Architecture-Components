package com.xcaret.android_kotlin_module.database

import androidx.room.RoomDatabase
import com.xcaret.android_kotlin_module.database.MoviesDatabase.Companion.DATABASE_VERSION
import com.xcaret.android_kotlin_module.models.Movie

@androidx.room.Database(
    entities = [Movie::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDAO

    companion object {
        const val DATABASE_NAME = "MoviesDatabase"
        const val DATABASE_VERSION = 1
    }
}