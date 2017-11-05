package com.bmpl.kotlinfirstapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //step-1: Downcasting
        //step-2: Attach listener
        //step-3: Attach Handler

        //Bitmap --> Predefined class --> BitmapFactory
        //3rd party tool --> Glide image library --> URL fetch
        // heavy image -->

        //Glide Link:
        //https://github.com/codepath/android_guides/wiki/Displaying-Images-with-the-Glide-Library

        button1.setOnClickListener {
          //handler -->
            //imageView.setImageResource(R.drawable.brain_game)
            Glide.with(this)
                    .load(R.drawable.brain_game)
                    .into(imageView)
        }

        button2.setOnClickListener{
            imageView.setImageResource(R.drawable.image2)
        }
    }
}

