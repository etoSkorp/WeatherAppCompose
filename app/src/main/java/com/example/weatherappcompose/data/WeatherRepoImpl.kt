package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.model.WeatherRemoteModel

class WeatherRepoImpl(private val api: WeatherApi) : WeatherRepo {

    override suspend fun getWeather() : WeatherRemoteModel {
        return api.getWeather(city = "Moscow")
    }
}