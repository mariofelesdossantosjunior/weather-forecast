package com.mario.forecast.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.mario.forecast.R
import java.text.SimpleDateFormat
import java.util.*

fun Date.formataDate() = SimpleDateFormat("EEEE, MMMM, yyyy",Locale.getDefault()).format(this)


fun Context.findIcon(icon: String) =
        when (icon) {
            "01d", "01n" -> ContextCompat.getDrawable(this, R.drawable.art_clear)
            "02d", "02n" -> ContextCompat.getDrawable(this, R.drawable.art_light_clouds)
            "03d", "03n" -> ContextCompat.getDrawable(this, R.drawable.art_clouds)
            "04d", "04n" -> ContextCompat.getDrawable(this, R.drawable.art_fog)
            "10d", "10n" -> ContextCompat.getDrawable(this, R.drawable.art_rain)
            "9d", "9n" -> ContextCompat.getDrawable(this, R.drawable.art_light_rain)
            "11d", "11n" -> ContextCompat.getDrawable(this, R.drawable.art_storm)
            "13d", "13n" -> ContextCompat.getDrawable(this, R.drawable.art_snow)
            else -> ContextCompat.getDrawable(this, R.drawable.art_clear)
        }