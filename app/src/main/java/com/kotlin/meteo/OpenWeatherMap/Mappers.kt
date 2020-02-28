package com.kotlin.meteo.OpenWeatherMap

import com.kotlin.meteo.Weather.Weather

fun mapOpenWeatherDataToWeather(weatherWrapper: WeatherWrapper) : Weather {
    val weather = weatherWrapper.weather.first()
    return Weather(
        description = weather.description,
        temperature = weatherWrapper.main.temp,
        temp_min = weatherWrapper.main.temp_min,
        temp_max = weatherWrapper.main.temp_max,
        humidity = weatherWrapper.main.humidity,
        pressure = weatherWrapper.main.pressure,
        iconUrl = "https://openweathermap.org/img/w/${weather.icon}.png",
        country = weatherWrapper.sys.country,
        sunrise = weatherWrapper.sys.sunrise,
        sunset = weatherWrapper.sys.sunset,
        speed = weatherWrapper.wind.speed,
        timezone = weatherWrapper.timezone,
        iconId = weather.id
    )
}