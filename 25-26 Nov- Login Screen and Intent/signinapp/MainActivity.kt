package com.bmpl.signinapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            var name = nameEditText.text.toString()
            var password  = passwordEditText.text.toString()

            //create an object of Intent class

            //explicit intent --> source and destination
            // MainActivity.this or this
            var intent = Intent(this, WelcomeActivity :: class.java)// when ever object is created a constructor is called
            // putExtra(Key, value)
            intent.putExtra("name", name)
            intent.putExtra("pass", password)
            startActivity(intent)
            finish()    // destroy the activity and dnt make it enter in STACK
            //startActivity()--> intent object
            //Intent intent = new Intent();
        }
    }
}