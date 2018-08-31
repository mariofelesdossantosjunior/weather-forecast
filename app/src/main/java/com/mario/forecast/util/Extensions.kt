package com.mario.forecast.util

import android.content.Context
import com.mario.forecast.R
import java.text.SimpleDateFormat
import java.util.*

fun Date.formataDate() = SimpleDateFormat("EEEE, MMMM, YYYY").format(this)


fun Context.findIcon(icon: String) =
        when (icon) {
            "01d", "01n" -> resources.getDrawable(R.drawable.art_clear)
            "02d", "02n" -> resources.getDrawable(R.drawable.art_light_clouds)
            "03d", "03n" -> resources.getDrawable(R.drawable.art_clouds)
            "04d", "04n" -> resources.getDrawable(R.drawable.art_fog)
            "10d", "10n" -> resources.getDrawable(R.drawable.art_rain)
            "9d", "9n" -> resources.getDrawable(R.drawable.art_light_rain)
            "11d", "11n" -> resources.getDrawable(R.drawable.art_storm)
            "13d", "13n" -> resources.getDrawable(R.drawable.art_snow)
            else -> resources.getDrawable(R.drawable.art_clear)
        }