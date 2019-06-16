package com.pawanjes.numbers.service.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

import com.pawanjes.numbers.R
import com.pawanjes.numbers.service.model.RetrofitResponse

object ErrorHandler {

    fun logError(error: RetrofitResponse<*>?) {

    }

    fun showErrorDialog(error: RetrofitResponse<*>?, context: Context, callback: DialogInterface.OnClickListener) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Error")
        if (error?.error != null) {
            alertDialogBuilder.setMessage(error.error!!.errorMsg)
        } else {
            alertDialogBuilder.setMessage("We are facing some difficulties and will be back")
        }
        alertDialogBuilder.setPositiveButton("OK", callback)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun showAppUpdateDialog(context: Context, message: String, callback: DialogInterface.OnClickListener) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton(R.string.app_update_button, callback)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    fun showGeneralDialog(
        context: Context,
        message: String,
        negativeText: String,
        positiveText: String,
        callback: DialogInterface.OnClickListener
    ) {
        val alerDialogBuilder = AlertDialog.Builder(context)
        alerDialogBuilder.setMessage(message)
        alerDialogBuilder.setPositiveButton(positiveText, callback)
        alerDialogBuilder.setNegativeButton(negativeText, callback)
        val alertDialog = alerDialogBuilder.create()
        alertDialog.setCancelable(false)
        alerDialogBuilder.show()
    }
}
