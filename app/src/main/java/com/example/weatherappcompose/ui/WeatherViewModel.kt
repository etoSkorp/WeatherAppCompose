package com.example.weatherappcompose.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.DataEvent
import com.example.weatherappcompose.ViewState
import com.example.weatherappcompose.base.BaseViewModel
import com.example.weatherappcompose.base.Event
import com.example.weatherappcompose.domain.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherInteractor: WeatherInteractor) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadWeather)
    }

    override fun initialViewState(): ViewState = ViewState(
        weatherList = WeatherModel(
            city = "",
            country = "",
            localTime = "",
            curTemp = 0F,
            isDay = 1,
            curCondition = "",
            curIcon = "",
            curWindKph = 0F
        ),
        daysList = listOf()
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadWeather -> {
                viewModelScope.launch {
                    weatherInteractor.getWeather().fold(
                        onError = {
                            Log.e("ERROR", it.localizedMessage)
                        },
                        onSuccess = {
                            processDataEvent(
                                DataEvent.OnLoadWeatherSucceed(it)
                            )
                        }
                    )
                }
                return null
            }
            is DataEvent.OnLoadWeatherSucceed -> {
                val list = weatherInteractor.getDaysList()

                return previousState.copy(
                    weatherList = event.weatherModel,
                    daysList = list
                )
            }
            else -> return null
        }
    }
}