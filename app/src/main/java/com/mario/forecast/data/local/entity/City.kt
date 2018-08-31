package com.mario.forecast.data.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("coord")
        val coord: Coord,

        @SerializedName("country")
        val country: String,

        @SerializedName("population")
        val population: Int
) : Parcelable