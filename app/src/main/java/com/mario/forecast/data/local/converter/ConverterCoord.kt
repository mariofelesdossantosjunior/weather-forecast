package com.mario.forecast.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mario.forecast.data.local.entity.Coord


class ConverterCoord {

    @TypeConverter
    fun coordToJson(coord: Coord?): String {
        return Gson().toJson(coord)
    }

    @TypeConverter
    fun jsonToCoord(data: String?): Coord? {
        return Gson().fromJson(data, Coord::class.java)
    }
}
