package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("forecastday")
    val forecastday: List<WeatherForecastDayList>
)
