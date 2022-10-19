package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.model.WeatherRemoteModel
import com.example.weatherappcompose.domain.WeatherModel

fun WeatherRemoteModel.toDomain() = WeatherModel(
    city = location.name,
    country = location.country,
    localTime = location.localTime,

    curTemp = current.curTemp,
    isDay = current.isDay,
    curCondition = current.curCondition.curCondition,
    curIcon = current.curCondition.curIcon,
    curWindKph = current.windKph
)