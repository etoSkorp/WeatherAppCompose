package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherLocation(
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("localtime")
    val localTime: String
)