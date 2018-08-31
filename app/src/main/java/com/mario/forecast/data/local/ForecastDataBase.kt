package com.mario.forecast.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mario.forecast.data.local.converter.*
import com.mario.forecast.data.local.dao.ForecastDao
import com.mario.forecast.data.local.entity.Forecast

@Database(entities = [
    (Forecast::class)
], version = 1, exportSchema = false)

@TypeConverters(ConverterDate::class,
        ConverterList::class,
        ConverterListDetail::class,
        ConverterListWeather::class,
        ConverterCoord::class)

abstract class ForecastDataBase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao
}