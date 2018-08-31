package com.mario.forecast.data.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Details(
        @SerializedName("dt") val dt: Int,
        @SerializedName("temp") val temp: Temp,
        @SerializedName("pressure") val pressure: Double,
        @SerializedName("humidity") val humidity: Double,
        @SerializedName("weather") val weather: List<Weather>,
        @SerializedName("speed") val speed: Double,
        @SerializedName("deg") val deg: Int,
        @SerializedName("clouds") val clouds: Int
) : Parcelable