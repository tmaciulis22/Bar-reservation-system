package com.barreservation.feature.barInfo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barreservation.R
import com.barreservation.extension.setBarImage
import com.barreservation.extension.setSupportActionBarWithNoTitle
import com.barreservation.network.models.Bar
import com.barreservation.feature.reserve.ReserveActivity
import kotlinx.android.synthetic.main.activity_bar_info.*

class BarInfoActivity : AppCompatActivity() {

    private val bar: Bar by lazy {
        intent.getParcelableExtra<Bar>(EXTRA_BAR)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_info)

        initView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initView() {
        setSupportActionBarWithNoTitle(toolbar)

        header.text = bar.name
        barImage.setBarImage(this, EXAMPLE_PHOTO_URI)
        averageRating.text = bar.averageRating.toString()
        address.text = bar.address
        phoneNumber.text = bar.phoneNumber
        bar.workingTime.let {
            monTime.text = getString(R.string.time, it.mon.opens, it.mon.closes)
            tueTime.text = getString(R.string.time, it.tue.opens, it.tue.closes)
            wedTime.text = getString(R.string.time, it.wed.opens, it.wed.closes)
            thuTime.text = getString(R.string.time, it.thu.opens, it.thu.closes)
            friTime.text = getString(R.string.time, it.fri.opens, it.fri.closes)
            satTime.text = getString(R.string.time, it.sat.opens, it.sat.closes)
            sunTime.text = getString(R.string.time, it.sun.opens, it.sun.closes)
        }

        reserveButton.setOnClickListener {
            startActivityForResult(
                ReserveActivity.newIntentForAdding(
                    this,
                    bar.id,
                    bar.name
                ), REQUEST_CODE_NEW_RESERVATION
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_NEW_RESERVATION)
            finish()
    }

    companion object {
        private const val EXTRA_BAR = "BarInfoActivity.EXTRA_BAR"
        private const val EXAMPLE_PHOTO_URI = "https://images.unsplash.com/photo-1514933651103-005eec06c04b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1567&q=80"
        const val REQUEST_CODE_NEW_RESERVATION = 100

        fun newIntent(
            context: Context,
            bar: Bar
        ): Intent =
            Intent(context, BarInfoActivity::class.java)
                .putExtra(EXTRA_BAR, bar)
    }
}