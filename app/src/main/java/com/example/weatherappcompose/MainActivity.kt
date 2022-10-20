package com.example.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherappcompose.navigation.Actions
import com.example.weatherappcompose.navigation.Destination
import com.example.weatherappcompose.ui.mainScreen.MainScreen
import com.example.weatherappcompose.ui.searchScreen.SearchScreen

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            val actions = remember(navController) { Actions(navController) }

            NavHost(navController = navController, startDestination = Destination.MainScreen) {
//                navController.currentDestination?.let {
//                    NavBackStackEntry.create(this@MainActivity,
//                        it)
//                }
                composable(Destination.MainScreen) { MainScreen(actions.openSearchScreen) }
                composable(Destination.SearchScreen) { SearchScreen(actions.openMainScreen) }
            }
        }
    }
}
