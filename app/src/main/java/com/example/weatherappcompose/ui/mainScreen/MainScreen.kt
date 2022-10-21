package com.example.weatherappcompose.ui.mainScreen

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ViewState
import com.example.weatherappcompose.ui.theme.GreyNight
import com.example.weatherappcompose.ui.theme.GreyNightSecond
import com.example.weatherappcompose.ui.theme.LightBlueDay
import com.example.weatherappcompose.ui.theme.WhiteMain
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    viewState: ViewState,
    onNavigateToSearchScreen: () -> Unit,
    onRefreshButtonClicked: () -> Unit

) {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
    val coroutineScope = rememberCoroutineScope()
    val localTime = remember { mutableStateOf("") }

    val tabList = listOf("Сегодня", "Завтра")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage

    @Composable
    fun getLocalTime() : String {
        coroutineScope.launch {
            val timer = object: CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    localTime.value = ZonedDateTime.now(ZoneId.of("Europe/Moscow")).format(formatter)
                }
                override fun onFinish() {
                    start()
                }
            }
            timer.start()
        }
        return localTime.value
    }

    Column() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().background(color = WhiteMain)
        ) {
            Box(modifier = Modifier.fillMaxHeight(0.6F)) {
                Image(
                    painter = painterResource(id = if (viewState.currentWeather.isDay == 1) R.drawable.day else R.drawable.night),
                    contentDescription = "daytime",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillHeight
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(top = 40.dp)
                ) {
                    Text(
                        text = getLocalTime(),
                        style = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.Serif),
                        color = GreyNightSecond
                    )
                    Text(
                        text = "${viewState.currentWeather.curTemp.toInt()}°",
                        style = TextStyle(fontSize = 72.sp, fontFamily = FontFamily.Serif),
                        color = GreyNightSecond
                    )
                    Text(
                        text = viewState.currentWeather.curCondition,
                        style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.Serif),
                        color = GreyNightSecond
                    )
                }
            }
            Row(
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search100x100),
                    contentDescription = "search",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onNavigateToSearchScreen() }
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = viewState.currentWeather.city,
                        style = TextStyle(fontSize = 24.sp, fontFamily = FontFamily.Serif),
                        color = if (viewState.currentWeather.isDay == 1) LightBlueDay else GreyNight
                    )
                    Text(
                        text = viewState.currentWeather.country,
                        style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif),
                        color = if (viewState.currentWeather.isDay == 1) LightBlueDay else GreyNight
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.refresh100x100),
                    contentDescription = "refresh",
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            onRefreshButtonClicked()

                        }
                )
            }
        }

        // TabRow with RecyclerView
        Column(modifier = Modifier.background(color = WhiteMain)) {
            TabRow(
                selectedTabIndex = tabIndex,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier.pagerTabIndicatorOffset(pagerState, it),
                        color = if (viewState.currentWeather.isDay == 1) LightBlueDay else GreyNight
                    )
                },
                backgroundColor = Color.Transparent,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                tabList.forEachIndexed { index, item ->
                    Tab(
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = item,
                                color = GreyNightSecond,
                                fontFamily = FontFamily.Serif,
                                fontSize = 15.sp
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                count = tabList.size,
                state = pagerState,
                modifier = Modifier
                    .weight(1.0f)
                    .padding(top = 10.dp)
            ) { dayIndex ->
                if (viewState.daysList.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(
                            viewState.daysList[dayIndex].hourList
                        ) { hourIndex, item ->
                            TabItem(item)
                        }
                    }
                }
            }
        }
    }
}
