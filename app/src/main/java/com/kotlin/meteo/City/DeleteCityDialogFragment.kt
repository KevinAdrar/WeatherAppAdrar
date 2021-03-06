package com.kotlin.meteo.City

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.kotlin.meteo.R


class DeleteCityDialogFragment : androidx.fragment.app.DialogFragment() {

    interface DeleteCityDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    companion object {
        val EXTRA_CITY_NAME = "com.kotlin.meteo.extras.EXTRA_CITY_NAME"

        fun newInstance(cityName: String) : DeleteCityDialogFragment {
            val fragment = DeleteCityDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(EXTRA_CITY_NAME, cityName)
            }

            return fragment
        }
    }

    var listener: DeleteCityDialogListener? = null

    private lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cityName = arguments!!.getString(EXTRA_CITY_NAME).toString()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)

        builder.setTitle(getString(R.string.deletecity_title, cityName))
            .setPositiveButton(getString(R.string.deletecity_positive)
            ) { _, _ -> listener?.onDialogPositiveClick() }
            .setNegativeButton(getString(R.string.deletecity_negative)
            ) { _, _ -> listener?.onDialogNegativeClick() }

        return builder.create()
    }
}