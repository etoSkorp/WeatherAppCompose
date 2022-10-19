package com.example.weatherappcompose.ui

import com.example.weatherappcompose.base.attempt
import com.example.weatherappcompose.data.WeatherRepo
import com.example.weatherappcompose.data.toDomain
import com.example.weatherappcompose.domain.DayModel
import com.example.weatherappcompose.domain.HourModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherInteractor(private val weatherRepo: WeatherRepo) {

    suspend fun getWeather() = attempt { weatherRepo.getWeather().toDomain() }

    suspend fun getDaysList() : List<DayModel> {
        val dayList = mutableListOf<DayModel>() // 3 элемента [0, 1, 2] (текущий день и 2 следующих)
        val forecastday = weatherRepo.getWeather().forecast.forecastday

        for (i in forecastday.indices) {
            val item = forecastday[i] // item - индекс дня (сегодня, завтра, послезавтра)
            val hour = item.hour // hour - индекс часа (24 часа с интервалом в час)
            val hourList = mutableListOf<HourModel>() // 24 HourModel для каждого дня (должно быть :) )

            for (j in hour.indices) {
                hourList.add(
                    HourModel(
                        time = hour[j].time,
                        hourCurTemp = hour[j].hourCurTemp,
                        hourCondition = hour[j].hourCondition.hourCondition,
                        hourIcon = hour[j].hourCondition.hourIcon,
                        hourWindKph = hour[j].hourWindKph
                    )
                )
            }

            dayList.add(
                DayModel(
                    date = item.date,
                    dayMaxTemp = item.day.dayMaxTemp,
                    dayMinTemp = item.day.dayMinTemp,
                    maxWindKph = item.day.maxWindKph,
                    dayCondition = item.day.dayCondition.dayCondition,
                    dayIcon = item.day.dayCondition.dayIcon,
                    sunrise = item.astro.sunrise,
                    sunset = item.astro.sunset,
                    hourList = hourList
                )
            )
        }
        return dayList
    }
}