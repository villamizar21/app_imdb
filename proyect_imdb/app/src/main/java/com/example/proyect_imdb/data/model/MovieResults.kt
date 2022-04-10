package com.example.proyect_imdb.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
class MovieResults() {

    @SerializedName("adult")
    var adult = false

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @PrimaryKey
    @SerializedName("id")
    var id : Long? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
    var originaltitle : String? = null

    @SerializedName("overview")
    var overview : String? = null

    @SerializedName("popularity")
    var popularity : Double? = null

    @SerializedName("poster_path")
    var posterpaht : String? = null

    @SerializedName("release_date")
    var realeasedate : String? = null

    @SerializedName("title")
    var title : String? = null

    @SerializedName("video")
    var video : Boolean? = null

    @SerializedName("vote_average")
    var voteAverage : Double? = null

    @SerializedName("vote_count")
    var voteCount :Int? = null

}
