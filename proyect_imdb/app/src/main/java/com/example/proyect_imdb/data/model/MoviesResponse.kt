package com.example.proyect_imdb.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class MoviesResponse():Parcelable {

    @PrimaryKey
    @SerializedName("page")
    var page = 0

    ////@SerializedName("results")
    ////var movies: List<MovieResults>? = null

    @SerializedName("total_results")
    var totalResults = 0

    @SerializedName("total_pages")
    var totalPages = 0

    constructor(parcel: Parcel) : this() {
        page = parcel.readInt()
        totalResults = parcel.readInt()
        totalPages = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeInt(totalResults)
        parcel.writeInt(totalPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviesResponse> {
        override fun createFromParcel(parcel: Parcel): MoviesResponse {
            return MoviesResponse(parcel)
        }

        override fun newArray(size: Int): Array<MoviesResponse?> {
            return arrayOfNulls(size)
        }
    }
}