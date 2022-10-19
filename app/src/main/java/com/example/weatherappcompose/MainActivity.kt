package com.example.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherappcompose.domain.DayModel
import com.example.weatherappcompose.domain.WeatherModel
import com.example.weatherappcompose.ui.WeatherViewModel
import com.example.weatherappcompose.ui.mainScreen.Header
import com.example.weatherappcompose.ui.mainScreen.HeaderDemo
import com.example.weatherappcompose.ui.mainScreen.TabLayout
import com.example.weatherappcompose.ui.mainScreen.TabLayoutDemo
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : ComponentActivity() {

//    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

//            val navController = rememberNavController()
//
//            NavHost(navController = navController, startDestination = "mainScreen") {
//                composable("mainScreen") {  }
//            }
//
//            val daysList = remember {
//                mutableStateOf(listOf<DayModel>())
//            }
//            val curWeather = remember {
//                mutableStateOf(
//                    WeatherModel(
//                        city = "",
//                        country = "",
//                        localTime = "",
//                        curTemp = 0F,
//                        isDay = 1,
//                        curCondition = "",
//                        curIcon = "",
//                        curWindKph = 0F
//                    )
//                )
//            }
//
//            viewModel.viewState.observe(this) {
//                render(it, daysList, curWeather)
//            }
//
//            Column {
//                Header(curWeather, viewModel)
//                TabLayout(daysList, curWeather.value.isDay)
//            }
            Column {
                HeaderDemo()
                TabLayoutDemo()
            }
        }
    }
}

//fun render(viewState: ViewState, daysList: MutableState<List<DayModel>>, curWeather: MutableState<WeatherModel>) {
//    daysList.value = viewState.daysList
//    curWeather.value = viewState.weatherList
//}

