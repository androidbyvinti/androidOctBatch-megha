package com.android.listview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_full_screen.*

class FullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        // Main2Activity mainActivity = new Main2Activity()

        var mainActivity = Main2Activity()  // object


        var intent = intent
        var pos = intent.getIntExtra("position", 0)

        fullImageView.setImageResource(mainActivity.imagesArray[pos])
        //fullImageView.setImageResource(pos)

    }
}
