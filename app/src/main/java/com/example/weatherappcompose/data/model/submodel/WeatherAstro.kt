package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherAstro(
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String
)
