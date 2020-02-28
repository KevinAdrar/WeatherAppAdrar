package com.kotlin.meteo.Weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kotlin.meteo.App
import com.kotlin.meteo.OpenWeatherMap.WeatherWrapper
import com.kotlin.meteo.OpenWeatherMap.mapOpenWeatherDataToWeather
import com.kotlin.meteo.R
import com.kotlin.meteo.Utils.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment: Fragment() {

    companion object {
        val EXTRA_CITY_NAME = "com.kotlin.meteo.extras.EXTRA_CITY_NAME"

        fun newInstance() = WeatherFragment()
    }

    private lateinit var cityName: String

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var city: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var weatherDescription: TextView
    private lateinit var temperature: TextView
    private lateinit var humidity: TextView
    private lateinit var pressure: TextView
    private lateinit var temp_min: TextView
    private lateinit var temp_max: TextView
    private lateinit var sunrise: TextView
    private lateinit var sunset: TextView
    private lateinit var speed: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        refreshLayout = view.findViewById(R.id.swipe_refresh)
        city = view.findViewById(R.id.city)
        weatherIcon = view.findViewById(R.id.weather_icon)
        weatherDescription = view.findViewById(R.id.weather_description)
        temperature = view.findViewById(R.id.temperature)
        humidity = view.findViewById(R.id.humidity_data)
        pressure = view.findViewById(R.id.pressure_data)
        temp_min = view.findViewById(R.id.temp_min_data)
        temp_max = view.findViewById(R.id.temp_max_data)
        sunrise = view.findViewById(R.id.sunrise_data)
        sunset = view.findViewById(R.id.sunset_data)
        speed = view.findViewById(R.id.wind_data)


        refreshLayout.setOnRefreshListener { refreshWeather() }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity?.intent!!.hasExtra(EXTRA_CITY_NAME)) {
            updateWeatherForCity(activity!!.intent.getStringExtra(EXTRA_CITY_NAME))
        }
    }

    private fun updateWeatherForCity(cityName: String) {
        this.cityName = cityName
        this.city.text = cityName

        if (!refreshLayout.isRefreshing) {
            refreshLayout.isRefreshing = true
        }

        val call = App.weatherService.getWeather("$cityName")
        call.enqueue(object : Callback<WeatherWrapper> {

            override fun onResponse(call: Call<WeatherWrapper>?, response: Response<WeatherWrapper>?) {
                refreshLayout.isRefreshing = false
                response?.body()?.let {
                    updateUi(mapOpenWeatherDataToWeather(it))
                }
            }

            override fun onFailure(call: Call<WeatherWrapper>, t: Throwable) {
                Log.e(TAG, "could not load city currentObservation", t)
                Toast.makeText(activity,
                    R.string.weather_message_error_could_not_load_weather,
                    Toast.LENGTH_SHORT).show()
                refreshLayout.isRefreshing = false
            }

        })
    }

    fun updateUi(weather: Weather) {
//        Picasso.get()
//            .load(weather.iconUrl)
//            .placeholder(R.drawable.ic_cloud_off_black_24dp)
//            .into(weatherIcon)
        Utilities.getIconUri(weather.iconId)?.let {
            val iconId = resources.getIdentifier(it, null, context?.packageName)

            val iconDrawable = resources.getDrawable(iconId)
            weatherIcon.setImageDrawable(iconDrawable)
        }

        weatherDescription.text = weather.description
        temperature.text = getString(R.string.weather_temperature_value, weather.temperature.toInt())
        temp_min.text = getString(R.string.weather_temperature_min_value, weather.temp_min.toInt())
        temp_max.text = getString(R.string.weather_temperature_max_value, weather.temp_max.toInt())
        humidity.text = getString(R.string.weather_humidity_value, weather.humidity)
        pressure.text = getString(R.string.weather_pressure_value, weather.pressure)
        sunrise.text =  getDateTime((weather.sunrise).toString())
        sunset.text =  getDateTime((weather.sunset).toString())
        speed.text = getString(R.string.wind_data, weather.speed)
    }

    private fun refreshWeather() {
        updateWeatherForCity(cityName)
    }

    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("kk:mm", Locale.FRANCE)
            val netDate = Date(s.toLong()*1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }

    }

}