package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherDayCondition(
    @SerializedName("text")
    val dayCondition: String,
    @SerializedName("icon")
    val dayIcon: String
)
