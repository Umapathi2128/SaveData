package com.example.yugan.savedata

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View

class SnackBarClass {

    @SuppressLint("ResourceAsColor")
    fun snackShow(view: View, message:String,context:Context) {
        val snackbar: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)

        snackbar.setAction("Ok", View.OnClickListener {
            snackbar.dismiss()
        })
        snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.lightYellow))
        snackbar.show()
    }
}