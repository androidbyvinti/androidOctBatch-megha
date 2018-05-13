package com.bmpl.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        resultTextView.text = sharedPreferences.getString("name", "")

        saveData.setOnClickListener {

            var name = nameEditText.text.toString()

           // var sharedPreferences : SharedPreferences.Editor = getSharedPreferences("data.txt", Context.MODE_PRIVATE).edit()
            var sharedPreferences : SharedPreferences.Editor = getPreferences(Context.MODE_PRIVATE).edit()
            sharedPreferences.putString("name", name)
            sharedPreferences.commit()   //synchronus
        }

    }
}