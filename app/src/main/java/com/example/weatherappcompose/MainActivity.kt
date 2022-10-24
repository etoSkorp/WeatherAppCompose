package com.example.weatherappcompose

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherappcompose.navigation.Destination
import com.example.weatherappcompose.ui.WeatherViewModel
import com.example.weatherappcompose.ui.mainScreen.AlertDialog
import com.example.weatherappcompose.ui.mainScreen.MainScreen
import com.example.weatherappcompose.ui.searchScreen.SearchScreen
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import org.koin.androidx.compose.getViewModel

class MainActivity() : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val openDialog = remember { mutableStateOf(false) }
            val navController = rememberNavController()

            val viewModel: WeatherViewModel = getViewModel()
            val viewState = viewModel.viewState.observeAsState()

            checkLocation(openDialog) { city -> viewModel.processDataEvent(DataEvent.LoadWeather(city)) }

            if (openDialog.value) {
                AlertDialog({ openIntent() }, openDialog = openDialog)
            }

            NavHost(navController = navController, startDestination = Destination.MainScreen) {
                composable(Destination.MainScreen) {
                    MainScreen(
                        viewState.value!!,
                        onNavigateToSearchScreen = {
                            navController.navigate(Destination.SearchScreen)
                        },
                        onRefreshButtonClicked = {
                            checkLocation(openDialog) { city -> viewModel.processDataEvent(DataEvent.LoadWeather(city)) }
                        }
                    )
                }
                composable(Destination.SearchScreen) {
                    SearchScreen(
                        viewState.value!!,
                        onNavigateToMainScreen = {
                            navController.navigate(Destination.MainScreen)
                        },
                        onClickedCityItemCallback = {
                            viewModel.processUIEvent(UIEvent.OnClickedCityItem(it))
                        },
                        onEnteredCharOnSearchTextCallback = {
                            viewModel.processUIEvent(UIEvent.EnteredCharOnSearchText(it))
                        },
                        clearTabItemInSearchScreen = {
                            viewModel.processUIEvent(UIEvent.ClearTabItemInSearchScreen)
                        }
                    )
                }
            }
        }
    }

    private fun openIntent() {
        ContextCompat.startActivity(
            this,
            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
            null
        )
    }

    private fun checkLocation(
        openDialog: MutableState<Boolean>,
        onCompleteResult: (String) -> Unit
    ) {
        if (isLocationEnabled()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationProviderClient
                .getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
                )
                .addOnCompleteListener {
                    onCompleteResult.invoke("${it.result.latitude},${it.result.longitude}")
                }
        } else {
            openDialog.value = true
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}
