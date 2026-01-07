package com.xcaret.android_kotlin_module.modules

import android.content.Context
import androidx.room.Room
import com.xcaret.android_kotlin_module.database.MovieDAO
import com.xcaret.android_kotlin_module.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MoviesDatabase.DATABASE_NAME
        ).build()

    @Provides
    fun providesMoviesDao(database: MoviesDatabase): MovieDAO = database.moviesDao()
}