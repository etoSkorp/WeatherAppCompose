package com.example.weatherappcompose.data

import com.example.weatherappcompose.API_KEY
import com.example.weatherappcompose.data.model.CityRemoteModel
import com.example.weatherappcompose.data.model.WeatherRemoteModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApi {

    @GET("search.json")
    @Headers("Content-type: application/json")
    suspend fun getCityName(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") cityName: String
    ): List<CityRemoteModel>
}