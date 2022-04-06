package com.example.proyect_imdb.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
class MovieResults() : Parcelable {

    @SerializedName("adult")
    var adult = false

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @PrimaryKey
    @SerializedName("id")
    var id : Int? = null

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

    constructor(parcel: Parcel) : this() {
        adult = parcel.readByte() != 0.toByte()
        posterpaht = parcel.readString()
        overview = parcel.readString()
        realeasedate = parcel.readString()
        id = parcel.readValue(Long::class.java.classLoader) as? Int
        originaltitle = parcel.readString()
        originalLanguage = parcel.readString()
        title = parcel.readString()
        backdropPath = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        video = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        voteAverage = parcel.readValue(Double::class.java.classLoader) as? Double
        voteCount = parcel.readValue(Int::class.java.classLoader) as? Int

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(posterpaht)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(realeasedate)
        parcel.writeValue(id)
        parcel.writeString(originaltitle)
        parcel.writeString(originalLanguage)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeValue(popularity)
        parcel.writeValue(voteCount)
        parcel.writeValue(video)
        parcel.writeValue(voteAverage)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieResults> {
        override fun createFromParcel(parcel: Parcel): MovieResults {
            return MovieResults(parcel)
        }

        override fun newArray(size: Int): Array<MovieResults?> {
            return arrayOfNulls(size)
        }
    }


}
