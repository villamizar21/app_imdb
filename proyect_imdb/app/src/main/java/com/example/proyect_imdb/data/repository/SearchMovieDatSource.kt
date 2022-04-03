package com.example.proyect_imdb.data.repository

import androidx.paging.PagingSource
import com.example.proyect_imdb.ConstValues
import com.example.proyect_imdb.ConstValues.API_KEY
import com.example.proyect_imdb.data.model.MovieResults
import com.example.proyect_imdb.data.remote.ApiService
import java.lang.Exception

class SearchMovieDatSource constructor(
    private val api: ApiService,
    private val searchQuery: String
):
    PagingSource<Int,MovieResults>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResults> {
        return try {
            val nextPage = params.key ?: 1
            val response = api.getMovieSearch(searchQuery,API_KEY, nextPage)
            val responseData = mutableListOf<MovieResults>()
            val data = response.results ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (nextPage == 1) null else nextPage - 1

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextPage.plus(1)
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}
