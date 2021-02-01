package com.example.nasaapp.ui.test_recycler_view.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapp.data.recycler.DataForRecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: DataForRecyclerView)
}