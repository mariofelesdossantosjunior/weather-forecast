package com.mario.forecast.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mario.forecast.data.local.entity.Weather


class ConverterListWeather {

    @TypeConverter
    fun listDetailToJson(weathers: List<Weather>?): String {
        return Gson().toJson(weathers)
    }

    @TypeConverter
    fun jsonTolistDetails(data: String?): List<Weather>? {
        val objects = Gson().fromJson(data, Array<Weather>::class.java) as Array<Weather>
        return objects.toList()
    }
}
