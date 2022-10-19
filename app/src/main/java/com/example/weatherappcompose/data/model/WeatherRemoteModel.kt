package com.example.weatherappcompose.data.model

import com.example.weatherappcompose.data.model.submodel.WeatherCurrent
import com.example.weatherappcompose.data.model.submodel.WeatherForecast
import com.example.weatherappcompose.data.model.submodel.WeatherLocation
import com.google.gson.annotations.SerializedName

data class WeatherRemoteModel(
    @SerializedName("location")
    val location: WeatherLocation,
    @SerializedName("current")
    val current: WeatherCurrent,
    @SerializedName("forecast")
    val forecast: WeatherForecast
)
