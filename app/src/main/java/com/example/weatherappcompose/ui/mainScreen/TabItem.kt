package com.example.weatherappcompose.ui.mainScreen

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.domain.HourModel

@Composable
fun TabItem(item: HourModel) {

    val context = LocalContext.current

    @DrawableRes
    fun getDrawableResID(context: Context): Int {
        val iconToInt = item.hourIcon.substringAfterLast("/").substringBefore(".")
        val isDayDrawableId = if (item.hourIsDay == 1) "day$iconToInt" else "night$iconToInt"
        return context.resources.getIdentifier(isDayDrawableId, "drawable", context.packageName)
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(20.dp) // Задел на будущее, когда по нажатии будет меняться фон элемента
            )

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            // Иконка погоды
            Image(
                painter = painterResource(id = getDrawableResID(context)),
                contentDescription = "weather",
                modifier = Modifier.size(60.dp)
            )
            Column(horizontalAlignment = Alignment.Start) {
                // Время
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.clock100x100),
                        contentDescription = "clock",
                        modifier = Modifier
                            .size(26.dp)
                            .padding(end = 5.dp)
                    )
                    Text(text = item.time.substringAfterLast(" "), fontSize = 14.sp)
                }
                // Температура
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.temparature100x100),
                        contentDescription = "temp",
                        modifier = Modifier
                            .size(26.dp)
                            .padding(end = 5.dp)
                    )
                    Text(text = "${item.hourCurTemp.toInt()}°C",
                        fontSize = 14.sp
                    )
                }
                // Скорость ветра
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.wind100x100),
                        contentDescription = "wind",
                        modifier = Modifier
                            .size(26.dp)
                            .padding(end = 5.dp)
                    )
                    Text(text = "${(item.hourWindKph / 3.6F).toInt()}м/с", fontSize = 14.sp)
                }
            }
        }
    }
}