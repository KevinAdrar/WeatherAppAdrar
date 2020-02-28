package com.kotlin.meteo.Utils

class Utilities {

    companion object {

        var soleil = "@drawable/sunny"
        var nuage_peu = "@drawable/clouds_and_sun"
        var nuage_normal = "@drawable/clouds_and_sun"
        var nuage_casse = "@drawable/clouds"
        var pluie_beaucoup = "@drawable/raining"
        var pluie_normal = "@drawable/raindrops"
        var eclair = "@drawable/bolt"
        var neige = "@drawable/snowflake"
        var brouillard = "@drawable/tornado"


        fun getIconUri(iconId: Int): String? {
            var toReturn = "@drawable/weather"
            if (iconId >= 200 && iconId <= 232) {
                toReturn = eclair
            }
            if (iconId >= 300 && iconId <= 321) {
                toReturn = pluie_beaucoup
            }
            if (iconId >= 500 && iconId <= 504) {
                toReturn = pluie_normal
            }
            if (iconId == 511) {
                toReturn = neige
            }
            if (iconId >= 520 && iconId <= 531) {
                toReturn = pluie_beaucoup
            }
            if (iconId >= 600 && iconId <= 622) {
                toReturn = neige
            }
            if (iconId >= 701 && iconId <= 781) {
                toReturn = brouillard
            }
            if (iconId == 800) {
                toReturn = soleil
            }
            if (iconId == 801) {
                toReturn = nuage_peu
            }
            if (iconId == 802) {
                toReturn = nuage_normal
            }
            if (iconId >= 803 && iconId <= 804) {
                toReturn = nuage_casse
            }
            return toReturn
        }
    }
}