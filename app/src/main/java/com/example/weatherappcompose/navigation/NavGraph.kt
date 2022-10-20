package com.example.weatherappcompose.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.weatherappcompose.navigation.Destination.MainScreen
import com.example.weatherappcompose.navigation.Destination.SearchScreen

object Destination {
    const val MainScreen = "MainScreen"
    const val SearchScreen = "SearchScreen"
}

class Actions(navController: NavHostController) {
    val openMainScreen: () -> Unit = { navController.navigate(MainScreen) }
    val openSearchScreen: () -> Unit = { navController.navigate(SearchScreen) }
}