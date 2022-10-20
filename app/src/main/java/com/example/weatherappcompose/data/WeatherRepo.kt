package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.model.CityRemoteModel
import com.example.weatherappcompose.data.model.WeatherRemoteModel

interface WeatherRepo {

    suspend fun getWeather(cityName: String) : WeatherRemoteModel
    suspend fun getCityName(cityName: String) : List<CityRemoteModel>
}