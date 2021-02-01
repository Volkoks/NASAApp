package com.example.nasaapp.data.retrofit.entity

import com.google.gson.annotations.SerializedName

data class ProjectsResponse(
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("lastUpdated") val lastUpdated: String?
)
