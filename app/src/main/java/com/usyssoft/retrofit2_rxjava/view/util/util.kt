package com.usyssoft.retrofit2_rxjava.view.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.usyssoft.retrofit2_rxjava.R

@SuppressLint("CheckResult")
fun ImageView.setImageGlide(context: Context, url: String?, placeholder: CircularProgressDrawable?, width: Int?=200, height: Int?=200) {
    val requestOptions = RequestOptions()
    //.override(width!!, height!!) // Thumbnail resize image w/h Pixel olarak
    if (placeholder != null) {
        requestOptions.placeholder(placeholder).error(R.drawable.ic_launcher_foreground)
    }


    if (url != null) {
        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(this@setImageGlide)
    } else {
        Glide.with(context)
            .load(placeholder)
            .into(this@setImageGlide)
    }
}
fun placeHolder(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}