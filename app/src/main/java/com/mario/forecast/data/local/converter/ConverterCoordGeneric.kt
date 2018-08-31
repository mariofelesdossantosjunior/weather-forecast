package com.mario.forecast.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mario.forecast.data.local.entity.Coord


class ConverterCoordGeneric {

    @TypeConverter
    fun coordToJson(coord: Coord?): String {
        return Gson().toJson(coord)
    }

    @TypeConverter
    fun jsonToCoord(data: String?): Coord? {
        val type = object : TypeToken<Coord>() {}.type
        return Gson().fromJson(data, type)
    }
}
