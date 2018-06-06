package com.bmpl.backgroundservice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var startServiceButton: Button
    lateinit var stopServiceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceButton = findViewById(R.id.startServiceButton) as Button
        stopServiceButton = findViewById(R.id.stopServiceButton) as Button

        startServiceButton.setOnClickListener(this)
        stopServiceButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.startServiceButton -> startService(Intent(this, MyService::class.java))

            R.id.stopServiceButton -> stopService(Intent(this, MyService::class.java))
        }
    }
}