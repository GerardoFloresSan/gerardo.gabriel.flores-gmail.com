package com.pruebas.pruebadedialogbeneficios

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.DialogFragment

class WelcomeNewDialogCustom : DialogFragment() {

    private lateinit var onClickListener: () -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_welcome_new_dialog, null)
        val closeDialog = dialogView.findViewById<AppCompatImageButton>(R.id.close_dialog)

        closeDialog.setOnClickListener {
            dismiss()
        }


        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        return builder.create()
    }

    fun setOnClickListener(onClickListener: () -> Unit) {
        this.onClickListener = onClickListener
    }
}