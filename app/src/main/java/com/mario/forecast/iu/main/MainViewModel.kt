package com.mario.forecast.iu.main

import androidx.lifecycle.ViewModel
import com.mario.forecast.BuildConfig
import com.mario.forecast.data.local.entity.Forecast
import com.mario.forecast.data.repository.RepositoryForecast

class MainViewModel(
        private val repositoryForecast: RepositoryForecast)
    : ViewModel() {

    /**
     * Fun load forecast API
     */
    fun findForecastByLatLon(lat: String, lon: String, appid: String =  BuildConfig.APP_ID) =
            repositoryForecast.findForecast(lat, lon, appid)

    /**
     * Fun load forecast Room DataBase
     */
    fun getForecast() = repositoryForecast.loadForecast()

    /**
     * Fun Save forecast Room DataBase
     */
    fun insertForecast(forecast: Forecast) = repositoryForecast.insertForecast(forecast)

    /**
     * Fun Update forecast Room DataBase
     */
    fun updateForecast(forecast: Forecast) = repositoryForecast.updateForecast(forecast)
}
