package com.example.nasaapp.data.retrofit.entity

import com.google.gson.annotations.SerializedName

data class MainProjects(
    @field:SerializedName("totalCount") val totalCount: Int,
    @field:SerializedName("projects") val projects: ProjectsResponse
)
