package com.example.weatherappcompose.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.DataEvent
import com.example.weatherappcompose.UIEvent
import com.example.weatherappcompose.ViewState
import com.example.weatherappcompose.base.BaseViewModel
import com.example.weatherappcompose.base.Event
import com.example.weatherappcompose.domain.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherInteractor: WeatherInteractor
    ) :
    BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadWeather)
    }

    override fun initialViewState(): ViewState = ViewState(
        currentWeather = WeatherModel(
            city = "Волжский",
            country = "",
            localTime = "",
            curTemp = 0F,
            isDay = 1,
            curCondition = "",
            curIcon = "",
            curWindKph = 0F
        ),
        daysList = listOf(),
        // Поиск
        cityNameList = listOf()
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadWeather -> {
                viewModelScope.launch {
                    weatherInteractor.getWeather(previousState.currentWeather.city).fold(
                        onError = {
                            Log.e("ERROR", it.localizedMessage)
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadWeatherSucceed(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnLoadWeatherSucceed -> {
                val list = weatherInteractor.getDaysList(event.weatherModel.city)

                return previousState.copy(
                    currentWeather = event.weatherModel,
                    daysList = list
                )
            }
            is UIEvent.EnteredCharOnSearchText -> {
                viewModelScope.launch {
                    weatherInteractor.getCityName(event.cityName).fold(
                        onError = {
                            Log.e("ERROR", it.localizedMessage)
                        },
                        onSuccess = {
                            Log.e("myLog", "${event.cityName} --- $it")
                            processDataEvent(DataEvent.OnEnteredCharOnSearchTextLoadSucceed(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnEnteredCharOnSearchTextLoadSucceed -> {
                return previousState.copy(
                    cityNameList = event.cityList
                )
            }
            is UIEvent.OnClickedCityItem -> {
                viewModelScope.launch {
                    weatherInteractor.getWeather(event.cityName).fold(
                        onError = {
                            Log.e("ERROR", it.localizedMessage)
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadWeatherSucceed(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnLoadWeatherWithChosenCitySucceed -> {
                val list = weatherInteractor.getDaysList(event.weatherModel.city)
                Log.e("qweasd", event.weatherModel.city)
                Log.e("qweasd", event.weatherModel.toString())

                return previousState.copy(
                    currentWeather = event.weatherModel,
                    daysList = list
                )
            }
            else -> return null
        }
    }
}