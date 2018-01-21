package com.android.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class CustomAdapter(main2Activity: Main2Activity, var imagesArray: IntArray) : BaseAdapter() {

    var context : Context = main2Activity

    var layoutInflater : LayoutInflater

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup?): View {
        var view = layoutInflater.inflate(R.layout.grid_view, viewGroup, false)
        var customImageView = view!!.findViewById<ImageView>(R.id.customImageView)
        customImageView.setImageResource(imagesArray[index])

        return view
    }

    override fun getItem(position: Int): Any  = imagesArray[position]

    override fun getItemId(id: Int): Long = imagesArray[id].toLong()

    override fun getCount(): Int  = imagesArray.size

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}