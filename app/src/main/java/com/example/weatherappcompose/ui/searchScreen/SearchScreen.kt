package com.example.weatherappcompose.ui.searchScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ViewState
import com.example.weatherappcompose.ui.theme.GreyNight

@Composable
fun SearchScreen(
    viewState: ViewState,
    onNavigateToMainScreen: () -> Unit,
    onClickedCityItemCallback: (cityName: String) -> Unit,
    onEnteredCharOnSearchTextCallback: (text: String) -> Unit,
    clearTabItemInSearchScreen: () -> Unit
) {
    var text by remember { mutableStateOf("") }

    val onClickedCityItem = { cityName: String ->
        onNavigateToMainScreen()
        onClickedCityItemCallback(cityName)
    }

    val requester = FocusRequester()

    SideEffect {
        requester.requestFocus()
    }

    Card(
        elevation = 5.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onEnteredCharOnSearchTextCallback(text)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .focusRequester(focusRequester = requester),
                textStyle = TextStyle(fontSize = 17.sp),
                placeholder = { Text(text = "Введите название города...", color = GreyNight) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search100x100),
                        contentDescription = "search",
                        modifier = Modifier.size(24.dp)
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            painter = painterResource(id = R.drawable.clear100x100),
                            contentDescription = "clear",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    text = ""
                                    clearTabItemInSearchScreen.invoke()
                                },
                        )
                    }
                },
            )
            if (text.isNotEmpty()) {
                AnimatedVisibility(
                    viewState.cityNameList.isNotEmpty(),
                    // Sets the initial height of the content to 20, revealing only the top of the content at
                    // the beginning of the expanding animation.
                    enter = expandVertically(
                        animationSpec = tween(durationMillis = 600),
                        expandFrom = Alignment.Top
                    ) { 20 },
                    // Shrinks the content to half of its full height via an animation.
                    exit = shrinkVertically(
                        animationSpec = tween(durationMillis = 600)
                    ) { 0 }
                ) {
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
                            Divider()
                        }
                    }
                }
            }
        }
    }
}


