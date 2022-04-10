package com.example.proyect_imdb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyect_imdb.data.dao.MovieDao
import com.example.proyect_imdb.data.model.MoviesResponse
import com.example.proyect_imdb.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [MoviesResponse::class], version = 1)
abstract class MovieDatabase: RoomDatabase(){

    abstract fun getMovieAppDao(): MovieDao

    class Callback @Inject constructor(
        private val databaseMovies: Provider<MovieDatabase>,
        @ApplicationScope private val ApplicationScope: CoroutineScope
    ):RoomDatabase.Callback()
}