package com.example.weatherappcompose.data.model

import com.google.gson.annotations.SerializedName

data class CityRemoteModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("country")
    val country: String
)