package com.mario.forecast.data.remote.api

import com.mario.forecast.data.local.entity.Forecast
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {

    @GET("/data/2.5/forecast/daily")
    fun getForecast(
            @Query("cnt") cnt: String = "7",
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("units") units: String = "metric",
            @Query("appid") appid: String
    ): Flowable<Forecast>

}
