package com.kotlin.meteo.Weather

data class Weather (val description: String,
                    val temperature: Float,
                    val temp_min: Float,
                    val temp_max: Float,
                    val humidity: Int,
                    val pressure: Int,
                    val iconUrl: String,
                    val country: String,
                    val sunrise: Int,
                    val sunset: Int,
                    val speed: Float,
                    val timezone: Int)
