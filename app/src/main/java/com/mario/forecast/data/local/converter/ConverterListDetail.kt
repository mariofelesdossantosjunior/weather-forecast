package com.mario.forecast.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mario.forecast.data.local.entity.Details


class ConverterListDetail {

    @TypeConverter
    fun listDetailToJson(details: List<Details>?): String {
        return Gson().toJson(details)
    }

    @TypeConverter
    fun jsonTolistDetails(data: String?): List<Details>? {
        val objects = Gson().fromJson(data, Array<Details>::class.java) as Array<Details>
        return objects.toList()
    }
}
