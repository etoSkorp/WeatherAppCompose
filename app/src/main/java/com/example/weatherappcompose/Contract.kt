package com.example.weatherappcompose

import com.example.weatherappcompose.base.Event
import com.example.weatherappcompose.domain.DayModel
import com.example.weatherappcompose.domain.WeatherModel

data class ViewState(
    val weatherList: WeatherModel,
    val daysList: List<DayModel>
)

sealed class DataEvent : Event {
    object LoadWeather : DataEvent()
    data class OnLoadWeatherSucceed(val weatherModel: WeatherModel) : DataEvent()
}

sealed class UIEvent : Event {
    object RefreshScreen : UIEvent()
}