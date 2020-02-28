package com.kotlin.meteo.OpenWeatherMap



data class WeatherWrapper(val weather: Array<WeatherData>,
                          val main: MainData,
                          val sys: CountryInfo,
                          val wind: WindSpeed,
                          val timezone: Int)

class WeatherData(val description: String,
                  val icon: String,
                  val id: Int)

data class MainData(val temp: Float,
                    val pressure: Int,
                    val humidity: Int,
                    val temp_min: Float,
                    val temp_max: Float)

data class CountryInfo(val country: String,
                       val sunrise: Int,
                       val sunset: Int)

data class WindSpeed(val speed: Float)
