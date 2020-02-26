package com.kotlin.meteo.City

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kotlin.meteo.App
import com.kotlin.meteo.R
import com.kotlin.meteo.Weather.WeatherActivity
import com.kotlin.meteo.Weather.WeatherFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CityActivity : AppCompatActivity(), CityFragment.CityFragmentListener {

    private lateinit var cityFragment: CityFragment
    private var currentCity: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        cityFragment = supportFragmentManager.findFragmentById(R.id.city_fragment) as CityFragment
        cityFragment.listener = this

    }

    override fun onCitySelected(city: City) {
        currentCity = city
        startWeatherActivity(city)
    }

    private fun startWeatherActivity(city: City) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(WeatherFragment.EXTRA_CITY_NAME, city.name)
        startActivity(intent)
    }

    override fun onEmptyCities() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
