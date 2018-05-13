package com.android.cardview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*


class RecyclerAdapter(private val mDataset: ArrayList<Data>)
    : RecyclerView.Adapter<RecyclerAdapter.DataObjectHolder>() {

    class DataObjectHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var label: TextView
        internal var dateTime: TextView

        init {
            label = itemView.findViewById(R.id.textView)
            dateTime = itemView.findViewById(R.id.textView2)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DataObjectHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_row, parent, false)

        return DataObjectHolder(view)
    }

    override fun onBindViewHolder(holder: DataObjectHolder, position: Int) {
        holder.label.text = mDataset[position].firstName
        holder.dateTime.text = mDataset[position].lastName
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}