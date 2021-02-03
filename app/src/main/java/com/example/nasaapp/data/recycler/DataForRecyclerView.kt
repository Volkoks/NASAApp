package com.example.nasaapp.data.recycler

data class DataForRecyclerView(
    val viewType: Int,
    val title: String,
    var url: String = "",
    var startData: String = "",
    var endData:String = ""
)
