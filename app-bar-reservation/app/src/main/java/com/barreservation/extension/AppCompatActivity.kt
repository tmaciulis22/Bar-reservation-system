package com.barreservation.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setSupportActionBarWithNoTitle(toolbar: Toolbar?) {
    setSupportActionBar(toolbar)
    supportActionBar?.apply {
        title = null
        setDisplayHomeAsUpEnabled(true)
        setDisplayShowHomeEnabled(true)
    }
}