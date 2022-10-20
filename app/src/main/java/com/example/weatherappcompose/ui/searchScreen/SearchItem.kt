package com.example.weatherappcompose.ui.searchScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.domain.CityModel

@Composable
fun SearchItem(
    item: CityModel,
    onClickedCityItem: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClickedCityItem.invoke(item.cityName)
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.LightGray,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = item.id.toString(), modifier = Modifier.padding(4.dp))
            Text(text = item.cityName, modifier = Modifier.padding(4.dp))
            Text(text = item.country, modifier = Modifier.padding(4.dp))
        }
    }
}