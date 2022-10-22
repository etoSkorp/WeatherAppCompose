package com.example.weatherappcompose.ui.searchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.R
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
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.city100x100),
                contentDescription = "city"
            )
            Column(
                modifier = Modifier.padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.cityName,
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 19.sp, fontFamily = FontFamily.Serif)
                )
                Text(
                    text = item.country,
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 15.sp, fontFamily = FontFamily.Serif)
                )
            }
        }
    }
}