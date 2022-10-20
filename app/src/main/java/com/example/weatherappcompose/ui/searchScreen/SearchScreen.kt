package com.example.weatherappcompose.ui.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.UIEvent
import com.example.weatherappcompose.ViewState
import com.example.weatherappcompose.ui.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(
    viewState: ViewState,
    onNavigateToMainScreen: () -> Unit,
    onClickedCityItemCallback: (cityName: String) -> Unit,
    onEnteredCharOnSearchTextCallback: (text: String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    val onClickedCityItem = { cityName: String ->
        onNavigateToMainScreen()
        onClickedCityItemCallback(cityName)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onNavigateToMainScreen,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Go back to MainScreen"
            )
        }
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onEnteredCharOnSearchTextCallback(text)
            },
            modifier = Modifier.padding(16.dp),
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            itemsIndexed(
                viewState.cityNameList
            ) { index, item ->
                SearchItem(
                    item = item,
                    onClickedCityItem = onClickedCityItem
                )
            }
        }
    }
}