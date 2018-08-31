package com.mario.forecast.iu.main

import android.content.Context
import com.mario.forecast.R
import com.mario.forecast.data.local.entity.Details
import com.mario.forecast.iu.adapter.BaseListAdapter
import com.mario.forecast.iu.adapter.BaseViewHolder
import com.mario.forecast.util.findIcon
import com.mario.forecast.util.formataDate
import kotlinx.android.synthetic.main.forecast_list_item.view.*
import java.util.*


class DetailAdapter(callbacks: (Details) -> Unit)
    : BaseListAdapter<Details>(callbacks) {

    override fun getListItemView(context: Context): BaseViewHolder<Details> {
        return ViewHolderToday(context)
    }


    inner class ViewHolderToday(context: Context?) : BaseViewHolder<Details>(context) {
        override fun layoutResId(): Int = R.layout.forecast_list_item

        override fun bind(item: Details) {
            tv_date.text = Date(item.dt.toLong() * 1000).formataDate()
            tv_description.text = item.weather[0].description
            tv_high_temperature.text = "${item.temp.max.toInt()} ºC"
            tv_low_temperature.text = "${item.temp.min.toInt()} ºC"
            img_icon.setImageDrawable(context.findIcon(item.weather[0].icon))
        }
    }

}

