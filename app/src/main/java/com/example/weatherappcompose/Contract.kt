package com.example.weatherappcompose

import com.example.weatherappcompose.base.Event
import com.example.weatherappcompose.domain.CityModel
import com.example.weatherappcompose.domain.DayModel
import com.example.weatherappcompose.domain.WeatherModel

data class ViewState(
    val currentWeather: WeatherModel,
    val daysList: List<DayModel>,
    val cityNameList: List<CityModel>
)

sealed class DataEvent : Event {
    data class LoadWeather(val city: String) : DataEvent()
    data class OnLoadWeatherSucceed(val weatherModel: WeatherModel) : DataEvent()
    data class OnEnteredCharOnSearchTextLoadSucceed(val cityList: List<CityModel>) : DataEvent()
    data class OnLoadWeatherWithChosenCitySucceed(val weatherModel: WeatherModel) : DataEvent()
}

sealed class UIEvent : Event {
    object RefreshMainScreen : UIEvent() // Unused for now
    object ClearTabItemInSearchScreen: UIEvent()
    data class EnteredCharOnSearchText(val cityName: String) : UIEvent() // Пользователь ввел любой символ в строке поиска
    data class OnClickedCityItem(val cityName: String) : UIEvent() // Пользователь нажал на определенный город из списка
}