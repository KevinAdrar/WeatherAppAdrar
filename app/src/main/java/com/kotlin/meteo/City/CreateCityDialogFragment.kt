package com.kotlin.meteo.City

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.kotlin.meteo.R

class CreateCityDialogFragment : androidx.fragment.app.DialogFragment() {

    interface CreateCityDialogListener {
        fun onDialogPositiveClick(cityName: String)
        fun onDialogNegativeClick()
    }

    var listener: CreateCityDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)

        val input = EditText(context)
        with(input) {
            inputType = InputType.TYPE_CLASS_TEXT
            hint = context.getString(R.string.createcity_cityhint)
        }

        builder.setTitle(R.string.createcity_title)
            .setView(input)
            .setPositiveButton(R.string.createcity_positive
            ) { _, _ ->
                listener?.onDialogPositiveClick(input.text.toString())
            }
            .setNegativeButton(R.string.createcity_negative
            ) { dialog, _ -> dialog.cancel()
                listener?.onDialogNegativeClick()}

        return builder.create()
    }

}