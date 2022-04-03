package com.example.proyect_imdb.data.repository

import com.example.proyect_imdb.data.remote.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: ApiService){
    fun getMovieSearch(searchQuery: String)= SearchMovieDatSource(api, searchQuery)
}