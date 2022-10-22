package com.example.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherappcompose.navigation.Destination
import com.example.weatherappcompose.ui.WeatherViewModel
import com.example.weatherappcompose.ui.mainScreen.MainScreen
import com.example.weatherappcompose.ui.searchScreen.SearchScreen
import org.koin.androidx.compose.getViewModel

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            val viewModel: WeatherViewModel = getViewModel()
            val viewState = viewModel.viewState.observeAsState()

            NavHost(navController = navController, startDestination = Destination.MainScreen) {
                composable(Destination.MainScreen) {
                    MainScreen(
                        viewState.value!!,
                        onNavigateToSearchScreen = { navController.navigate(Destination.SearchScreen) },
                        onRefreshButtonClicked = { viewModel.processDataEvent(DataEvent.LoadWeather) }
                    )
                }
                composable(Destination.SearchScreen) {
                    SearchScreen(
                        viewState.value!!,
                        onNavigateToMainScreen = { navController.navigate(Destination.MainScreen) },
                        onClickedCityItemCallback = { viewModel.processUIEvent(UIEvent.OnClickedCityItem(it)) },
                        onEnteredCharOnSearchTextCallback = { viewModel.processUIEvent(UIEvent.EnteredCharOnSearchText(it)) },
                        clearTabItemInSearchScreen = { viewModel.processUIEvent(UIEvent.ClearTabItemInSearchScreen) }
                    )
                }
            }
        }
    }
}
