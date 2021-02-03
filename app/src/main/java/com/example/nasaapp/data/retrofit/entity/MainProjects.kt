package com.example.nasaapp.data.retrofit.entity

import com.google.gson.annotations.SerializedName

data class MainProjects(
    @field:SerializedName("project") val project: FullInfoProject
)
