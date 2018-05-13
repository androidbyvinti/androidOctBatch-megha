package com.android.cardview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null // LinearLayout , GridLayout


    private val dataSet: ArrayList<Data>
        get() {
            val results = ArrayList<Data>()
            for (index in 0..19) {
                val obj = Data("Ram $index",
                        "Kumar $index")
                results.add(index, obj)
            }
            return results
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recyclerView)
        mLayoutManager = LinearLayoutManager(this) // LinearLayoutManager, GridViewManager, StaggeredGridViewManager
        mRecyclerView!!.layoutManager = mLayoutManager
        mAdapter = RecyclerAdapter(dataSet)
        mRecyclerView!!.adapter = mAdapter

    }
}
