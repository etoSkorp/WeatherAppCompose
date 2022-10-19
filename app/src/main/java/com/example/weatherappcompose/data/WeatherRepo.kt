package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.model.WeatherRemoteModel

interface WeatherRepo {

    suspend fun getWeather() : WeatherRemoteModel
}