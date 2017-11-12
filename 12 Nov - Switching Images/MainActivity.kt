package com.bmpl.switchingimage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clicked = false

        button.setOnClickListener {

            if(!clicked){
                button.text = "Hide Image"
                imageView.visibility = View.VISIBLE
                //imageView.setImageResource(R.mipmap.ic_launcher)
            }
            else {
                button.text = "Show Image"
                imageView.visibility = View.INVISIBLE
                //imageView.setImageResource(0)
            }
            clicked = !clicked
        }
    }
}
