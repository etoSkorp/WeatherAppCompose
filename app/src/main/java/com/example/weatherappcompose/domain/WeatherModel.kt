package com.example.weatherappcompose.domain

data class WeatherModel(
    val city: String, // Город (name)
    val country: String, // Страна (country)
    val localTime: String, // Текущее время

    // Погода в текущий день
    val curTemp: Float, // Текущая температура воздуха по цельсию (temp_c)
    val isDay: Int, // 1 = Yes, 0 = No, показывает, день сейчас или ночь (is_day)
    val curCondition: String, // Текущие погодные условия (text)
    val curIcon: String, // Текущая иконка погоды (icon)
    val curWindKph: Float, // Текущая скорость ветра
)

data class DayModel(
    // Погода в последующие дни
    val date: String, // Дата
    val dayMaxTemp: Float, // Максимальная температура (в определенный день)
    val dayMinTemp: Float, // Минимальная температура (в определенный день)
    val maxWindKph: Float, // Макс. скор. ветра в переменной date
    val dayCondition: String, // Погодные условия в переменной date
    val dayIcon: String, // Иконка в переменной date
    val sunrise: String, // Рассвет
    val sunset: String, // Закат
    val hourList: List<HourModel>
)

data class HourModel(
    // Погода по часам
    val time: String, // Время по часам (time)
    val hourCurTemp: Float, // Температура в текущий час в переменной time
    val hourCondition: String, // Погодные условия в текущий час в переменной time
    val hourIcon: String, // Иконка в текущий час в переменной time
    val hourWindKph: Float // Скорость верта в текущий час в переменной time
)