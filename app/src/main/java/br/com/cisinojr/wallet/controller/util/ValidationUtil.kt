package br.com.cisinojr.wallet.controller.util

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View

/**
 *
 * Created by cisino.gomes on 2/14/2019
 *
 */
class ValidationUtil private constructor() {

    /**
     * Show validation error messages
     */
    fun showSnackFeedback(context: Context, message: String?, isValid: Boolean, view: View) {
        val snackbar: Snackbar = Snackbar.make(view, message!!, Snackbar.LENGTH_SHORT)
        val v: View = snackbar.view

        if (!isValid) {
            v.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        } else {
            v.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        }

        snackbar.show()
    }


    companion object {
        private var instance: ValidationUtil? = null

        fun getInstance(): ValidationUtil {
            if (instance === null) {
                instance = ValidationUtil()
            }

            return instance as ValidationUtil
        }
    }
}