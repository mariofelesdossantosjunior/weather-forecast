package com.mario.forecast.data.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Temp(
        @SerializedName("day") val day: Double,
        @SerializedName("min") val min: Double,
        @SerializedName("max") val max: Double,
        @SerializedName("night") val night: Double,
        @SerializedName("eve") val eve: Double,
        @SerializedName("morn") val morn: Double
) : Parcelable