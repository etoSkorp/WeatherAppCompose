package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherDay(
    @SerializedName("maxtemp_c")
    val dayMaxTemp: Float,
    @SerializedName("mintemp_c")
    val dayMinTemp: Float,
    @SerializedName("maxwind_kph")
    val maxWindKph: Float,
    @SerializedName("condition")
    val dayCondition: WeatherDayCondition
)
