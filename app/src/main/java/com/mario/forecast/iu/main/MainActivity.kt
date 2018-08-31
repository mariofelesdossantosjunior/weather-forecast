package com.mario.forecast.iu.main

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationRequest
import com.google.android.material.snackbar.Snackbar
import com.mario.forecast.R
import com.mario.forecast.data.local.entity.Forecast
import com.mario.forecast.util.findIcon
import com.mario.forecast.util.formataDate
import com.maxcruz.reactivePermissions.ReactivePermissions
import com.maxcruz.reactivePermissions.entity.Permission
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider
import java.util.*


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_LOCATION = 1

    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val adapter: DetailAdapter by lazy { DetailAdapter { it } }

    private val locationPermission: Permission by lazy { Permission(Manifest.permission.ACCESS_FINE_LOCATION, R.string.acess_location, false) }

    private val reactivePermission: ReactivePermissions by lazy { ReactivePermissions(this, REQUEST_CODE_LOCATION) }

    private val locationProvider: ReactiveLocationProvider by lazy { ReactiveLocationProvider(this) }

    private val locationRequest: LocationRequest by lazy {
        LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(100)
    }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecycle()
        viewModel.getForecast().observe(this, Observer { it?.let { showForecast(it) } })
        getLocation()
    }

    /**
     * Function responsible from permissions request
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun getLocation() {
        reactivePermission.observeResultPermissions().subscribe {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (it.first == Manifest.permission.ACCESS_FINE_LOCATION && it.second) {
                    findLocation()
                }
            } else {
                findLocation()
            }
        }

        reactivePermission.evaluate(listOf(locationPermission))
    }

    /**
     * Function responsable for finding Latitude and Longitude
     */
    private fun findLocation() {
        showProgress(true)

        disposable.add(locationProvider.lastKnownLocation.subscribe {
            it?.let {
                findForecast(it.latitude.toString(), it.longitude.toString())
            }
        })

        disposable.add(locationProvider.getUpdatedLocation(locationRequest).subscribe {
            it?.let {
                findForecast(it.latitude.toString(), it.longitude.toString())
            }
        })
    }

    /**
     * Function responsable for finding Forecast from OpenweathermapAPI
     */
    fun findForecast(lat: String, lon: String) {
        disposable.add(
                viewModel.findForecastByLatLon(lat, lon)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnEach { showProgress(false) }
                        .subscribe(
                                { viewModel.insertForecast(it) },
                                {
                                    Snackbar.make(container,
                                            R.string.connection_failure,
                                            Snackbar.LENGTH_LONG).show()
                                }
                        )
        )


    }

    /**
     * Function resposable from update view
     */
    private fun showForecast(forecast: Forecast) {
        showTopInformation(forecast)
        adapter.clearItem()
        adapter.addItems(forecast.list)
    }

    /**
     * Function resposable from update view
     */
    private fun showTopInformation(forecast: Forecast) {
        tv_city_today.text = forecast.city.name
        tv_date_today.text = Date(forecast.list[0].dt.toLong() * 1000).formataDate()
        tv_description_today.text = forecast.list[0].weather[0].description
        tv_high_temperature_today.text = "${forecast.list[0].temp.max.toInt()} ºC"
        tv_low_temperature_today.text = "${forecast.list[0].temp.min.toInt()} ºC"
        img_icon_today.setImageDrawable(findIcon(forecast.list[0].weather[0].icon))
    }

    fun showProgress(show: Boolean) {
        pb_loading.visibility = if (show) View.VISIBLE else View.GONE
    }

    /**
     * Function resposable setup recycleView
     */
    private fun setupRecycle() {
        rv_forecast.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_forecast.setHasFixedSize(true)
        rv_forecast.adapter = adapter
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_LOCATION) {
            reactivePermission.receive(permissions, grantResults)
        }
    }

    override fun onStart() {
        super.onStart()
        getLocation()
    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
