package com.bmpl.switchingimage

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var countDownTimer: CountDownTimer = object : CountDownTimer(10100, 1000) {
            override fun onTick(l: Long) {
                textView.text = (l/1000).toString()
            }

            override fun onFinish() {

                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

                vibrator.vibrate(500)

                textView.visibility = View.INVISIBLE
                firstImage.visibility = View.VISIBLE
                secondImage.visibility = View.VISIBLE
                thirdImage.visibility = View.VISIBLE
            }
        }
        countDownTimer.start()
    }
}