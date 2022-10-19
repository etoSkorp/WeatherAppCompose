package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherForecastDayList(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: WeatherDay,
    @SerializedName("astro")
    val astro: WeatherAstro,
    @SerializedName("hour")
    val hour: List<WeatherHourList>
)
