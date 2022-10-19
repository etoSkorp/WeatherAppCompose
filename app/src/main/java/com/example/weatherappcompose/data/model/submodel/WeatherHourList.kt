package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherHourList(
    @SerializedName("time")
    val time: String,
    @SerializedName("temp_c")
    val hourCurTemp: Float,
    @SerializedName("condition")
    val hourCondition: WeatherHourCondition,
    @SerializedName("wind_kph")
    val hourWindKph: Float
)
