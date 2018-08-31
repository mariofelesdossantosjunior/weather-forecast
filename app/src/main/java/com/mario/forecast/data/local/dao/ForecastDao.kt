package com.mario.forecast.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mario.forecast.data.local.entity.Forecast

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: Forecast): Long

    @Update
    fun updateForecast(forecast: Forecast)

    @Delete
    fun deleteForecast(forecast: Forecast)

    @Query("SELECT * FROM forecast ORDER BY cod ASC LIMIT 1")
    fun loadForecast(): LiveData<Forecast>

    @Query("SELECT * FROM forecast WHERE cod = :id")
    fun getForecastByCode(id: String): LiveData<Forecast?>

    @Query("DELETE FROM forecast")
    fun removeAll()
}