package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherCurrent(
    @SerializedName("temp_c")
    val curTemp: Float,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("condition")
    val curCondition: WeatherCondition,
    @SerializedName("wind_kph")
    val windKph: Float
)
