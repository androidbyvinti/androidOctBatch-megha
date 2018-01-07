package com.android.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val DELAY_MILLISECONDS = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handler (time duration) --> execute --> android.os--> time duration

        // handle thread
        // inherit Thread class directly --> start , running, dead
        // Runnable Interface --> run() --> when thread's working is start
        var handler = Handler()
        // handler--> postDelayed(Runnable (Interface), long delayMilliSeconds)

        var run = Runnable {
            var intent = Intent(this@MainActivity, MenuActivity :: class.java)
            startActivity(intent)
            finish()
        }
        handler.postDelayed(run, DELAY_MILLISECONDS)

    }
}