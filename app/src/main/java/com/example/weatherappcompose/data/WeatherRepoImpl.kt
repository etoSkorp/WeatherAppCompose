package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.model.CityRemoteModel
import com.example.weatherappcompose.data.model.WeatherRemoteModel

class WeatherRepoImpl(private val api: WeatherApi, private val search: SearchApi) : WeatherRepo {

    override suspend fun getWeather(cityName: String): WeatherRemoteModel {
        return api.getWeather(city = cityName)
    }

    override suspend fun getCityName(cityName: String): List<CityRemoteModel> {
        return search.getCityName(cityName = cityName)
    }
}