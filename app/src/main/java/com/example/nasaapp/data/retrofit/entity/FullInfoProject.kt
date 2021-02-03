package com.example.nasaapp.data.retrofit.entity

import com.google.gson.annotations.SerializedName

data class FullInfoProject(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("lastUpdated") val lastUpdated: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("status") val status: String,
    @field:SerializedName("startDate") val startDate: String,
    @field:SerializedName("endDate") val endDate: String,
    @field:SerializedName("description") val description: String,

)
