package com.barreservation.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setBarImage(context: Context, imgResource: String) {
    Glide.with(context).load(imgResource).into(this)
}