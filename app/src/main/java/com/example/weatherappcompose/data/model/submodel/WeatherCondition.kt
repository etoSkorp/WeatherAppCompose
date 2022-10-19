package com.example.weatherappcompose.data.model.submodel

import com.google.gson.annotations.SerializedName

data class WeatherCondition(
    @SerializedName("text")
    val curCondition: String,
    @SerializedName("icon")
    val curIcon: String
)
