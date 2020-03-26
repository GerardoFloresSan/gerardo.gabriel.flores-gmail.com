package com.pruebas.pruebadedialogbeneficios

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.DialogFragment

class DialogCustom : DialogFragment() {

    var title: String = ""
    var listString: List<String> = arrayListOf()
    private lateinit var onClickListener: (List<String?>) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_custom, null)
        val closeDialog = dialogView.findViewById<AppCompatImageButton>(R.id.close_dialog)
        val back = dialogView.findViewById<AppCompatImageButton>(R.id.back)
        val next = dialogView.findViewById<AppCompatImageButton>(R.id.next)
        val text = dialogView.findViewById<TextView>(R.id.text)
        var current = 0

        text.text = listString[current]

        back.setOnClickListener {
            if (current > 0) {
                current -= 1
                text.text = listString[current]
            } else {
                current = 0
                text.text = listString[current]
            }
            next.visibility = View.VISIBLE
            back.visibility = if (current != 0) View.GONE else View.VISIBLE
        }
        next.setOnClickListener {
            if (current < listString.size) {
                current += 1
                text.text = listString[current]
            } else {
                current = listString.size + 1
                text.text = listString[current]
            }
            back.visibility = View.VISIBLE
            next.visibility = if (listString.size != (current + 1)) View.GONE else View.VISIBLE
        }

        closeDialog.setOnClickListener {
            dismiss()
        }
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        return builder.create()
    }

    fun setOnClickListener(onClickListener: (List<String?>) -> Unit) {
        this.onClickListener = onClickListener
    }


}