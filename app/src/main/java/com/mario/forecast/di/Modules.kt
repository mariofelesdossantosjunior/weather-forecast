package com.mario.forecast.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.mario.forecast.data.local.ForecastDataBase
import com.mario.forecast.data.remote.api.OpenWeatherAPI
import com.mario.forecast.data.repository.RepositoryForecast
import com.mario.forecast.iu.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Koin main module
 */
val module = applicationContext {

    bean {
        Room.databaseBuilder(androidApplication(),
                ForecastDataBase::class.java,
                "forecast-db")
                .allowMainThreadQueries()
                .build()
    }

    bean {

        val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(okHttpClient)
                .build()
    }

    bean { get<Retrofit>().create(OpenWeatherAPI::class.java) }

    bean { get<ForecastDataBase>().forecastDao() }

    viewModel { MainViewModel(get()) }

    bean { RepositoryForecast(get(), get()) }

}

/**
 * module list
 */
val appModules = listOf(module)
