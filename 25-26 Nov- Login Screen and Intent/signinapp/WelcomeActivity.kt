package com.bmpl.signinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        var newIntent = intent //getIntent(); //check which activity is redirecting data to the current activity
        textView.text = newIntent.getStringExtra("name").plus(" " +newIntent.getStringExtra("pass"))

    }
}
