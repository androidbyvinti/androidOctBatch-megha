package com.android.listview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    var imagesArray = intArrayOf(R.drawable.android, R.drawable.classroom,
                                R.drawable.clock, R.drawable.cplusplus,
                                R.drawable.dollar, R.drawable.soccer,
                                R.drawable.wine, R.drawable.cprogm)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var customAdapter = CustomAdapter(this, imagesArray)

        gridView.adapter = customAdapter

        gridView.setOnItemClickListener { adapterView, view, position, id ->
            var intent = Intent(this, FullScreenActivity :: class.java)
            intent.putExtra("position", position)
            //intent.putExtra("position", imagesArray[position])
            startActivity(intent)
        }

    }
}
