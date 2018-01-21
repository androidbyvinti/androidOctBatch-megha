package com.android.listview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemsList = arrayOf("Data 1", "Data 1", "Data 1", "Data 1",
                                    "Data 1", "Data 1", "Data 1", "Data 1",
                                    "Data 1", "Data 1", "Data 1", "Data 1",
                                    "Data 1", "Data 1", "Data 1", "Data 1",
                                    "Data 1", "Data 1", "Data 1", "Data 1",
                                    "Data 1", "Data 1", "Data 1", "Data 1",
                                    "Data 1", "Data 1", "Data 1", "Data 1",
                                    "Data 1", "Data 1", "Data 1", "Data 1")


        var dataAdapter = ArrayAdapter(this, R.layout.custom_listview, itemsList)

        listView.adapter = dataAdapter

        listView.setOnItemClickListener { _, _, position, _ ->

            Toast.makeText(this, "You clicked on ${itemsList[position]}", Toast.LENGTH_LONG).show()

        }

    }
}
