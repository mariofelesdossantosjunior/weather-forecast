package com.mario.forecast.data.repository

import com.mario.forecast.data.local.dao.ForecastDao
import com.mario.forecast.data.local.entity.Forecast
import com.mario.forecast.data.remote.api.OpenWeatherAPI

class RepositoryForecast(
        private val openWeatherAPI: OpenWeatherAPI,
        private val forecastDao: ForecastDao)
    : IRepository {

    override fun findForecast(lat: String, lon: String, appid: String) = openWeatherAPI.getForecast(lat = lat, lon = lon, appid = appid)

    override fun loadForecast() = forecastDao.loadForecast()

    override fun updateForecast(forecast: Forecast) = forecastDao.updateForecast(forecast)

    override fun insertForecast(forecast: Forecast) = forecastDao.insertForecast(forecast)

}