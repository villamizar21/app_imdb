package com.example.proyect_imdb.data.repository

import com.example.proyect_imdb.util.ConstValues.API_KEY
import com.example.proyect_imdb.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val api: ApiService) {

    fun getMovieDetails(movieId: Long) = flow {
        emit(api.getMovie(movieId, API_KEY))
    }.flowOn(Dispatchers.IO)

}