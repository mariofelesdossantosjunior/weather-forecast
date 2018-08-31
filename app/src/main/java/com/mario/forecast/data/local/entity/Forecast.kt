package com.mario.forecast.data.local.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "forecast")
data class Forecast(
        @PrimaryKey
        @SerializedName("cod")
        val cod: String,

        @Embedded
        @SerializedName("city")
        val city: City,

        @SerializedName("message")
        val message: Double,

        @SerializedName("cnt")
        val cnt: Int,

        @SerializedName("list")
        val list: List<Details>
) : Parcelable