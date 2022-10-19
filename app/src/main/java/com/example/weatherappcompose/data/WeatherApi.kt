package com.example.weatherappcompose.data

import com.example.weatherappcompose.API_KEY
import com.example.weatherappcompose.data.model.WeatherRemoteModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json")
    @Headers("Content-type: application/json")
    suspend fun getWeather(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") city: String,
        @Query("days") days: String = "2",
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): WeatherRemoteModel
}