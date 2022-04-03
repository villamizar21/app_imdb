package com.example.proyect_imdb.data.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val page: Int,
    @field:SerializedName("results")
    val results: List<MovieResults>,
    @field:SerializedName("total_pages")
    val totalpages: Int,
    @field:SerializedName("total_results")
    val totalresults: Int
)