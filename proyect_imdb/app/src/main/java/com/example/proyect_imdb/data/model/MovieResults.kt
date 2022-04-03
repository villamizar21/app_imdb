package com.example.proyect_imdb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MovieResults(
    val adult: Boolean,
    @field:SerializedName("backdrop_path")
    val backdropPath: String,
    @field:SerializedName("genre_ids")
    val genre_ids: List<String>,
    @PrimaryKey
    val id: Int,
    @field:SerializedName("original_language")
    val original_language: String,
    @field:SerializedName("original_title")
    val original_title: String,
    val overview: String,
    val popularity: String,
    @field:SerializedName("poster_path")
    val poster_paht: String,
    @field:SerializedName("release_date")
    val realease_date: String,
    val title: String,
    val video: Boolean,
    @field:SerializedName("vote_average")
    val vote_average: Double,
    @field:SerializedName("vote_count")
    val vote_acount: Int

)
