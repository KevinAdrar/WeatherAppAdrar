package com.kotlin.meteo.City


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kotlin.meteo.R


class CityAdapter(private val cities: List<City>,
                  private val cityListener: CityItemListener)
    : androidx.recyclerview.widget.RecyclerView.Adapter<CityAdapter.ViewHolder>(), View.OnClickListener {

    interface CityItemListener {
        fun onCitySelected(city: City)
        fun onCityDeleted(city: City)
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<androidx.cardview.widget.CardView>(R.id.card_view)!!
        val cityNameView = itemView.findViewById<TextView>(R.id.name)!!
        val deleteView = itemView.findViewById<View>(R.id.delete)!!
        val temperature = itemView.findViewById<TextView>(R.id.temperature)
        val weatherIcon = itemView.findViewById<ImageView>(R.id.weather_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        with(holder) {
            cardView.setOnClickListener(this@CityAdapter)
            cardView.tag = city
            cityNameView.text = city.name
            deleteView.tag = city
            deleteView.setOnClickListener(this@CityAdapter)
            //temperature.text =
        }
    }

    override fun getItemCount(): Int = cities.size

    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_view -> cityListener.onCitySelected(v.tag as City)
            R.id.delete -> cityListener.onCityDeleted(v.tag as City)
        }
    }
}