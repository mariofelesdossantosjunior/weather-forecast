package com.mario.forecast.data.repository

import androidx.lifecycle.LiveData
import com.mario.forecast.data.local.entity.Forecast
import io.reactivex.Flowable

interface IRepository {

    fun findForecast(lat: String, lon: String, appid: String): Flowable<Forecast>

    fun loadForecast(): LiveData<Forecast>

    fun updateForecast(forecast: Forecast)

    fun insertForecast(forecast: Forecast): Long

}