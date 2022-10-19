package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherHourCondition(
    @SerializedName("text")
    val hourCondition: String,
    @SerializedName("icon")
    val hourIcon: String
)
