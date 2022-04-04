package com.example.proyect_imdb.data.remote

import com.example.proyect_imdb.util.ConstValues
import com.example.proyect_imdb.data.model.MovieDetail
import com.example.proyect_imdb.data.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = ConstValues.API_KEY,
        @Query("page") page: Int
    ):MovieModel

    @GET("movie{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = ConstValues.API_KEY,
    ): MovieDetail

    @GET("search/movie")
    suspend fun getMovieSearch(
        @Query("query") query: String,
        @Query("api_key") apiKey:  String = ConstValues.API_KEY,
        @Query("page") page: Int
    ):MovieModel

}