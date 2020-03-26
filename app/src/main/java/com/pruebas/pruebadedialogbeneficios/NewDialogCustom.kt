package com.pruebas.pruebadedialogbeneficios

import android.animation.ValueAnimator
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.new_dialog_custom.*


class NewDialogCustom(valueOld: Int, valueNew: Int) : DialogFragment() {

    var title: String = ""
    var valor1 = valueOld
    var valor2 = valueNew

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.new_dialog_custom, null)

        val texto = dialogView.findViewById<TextView>(R.id.medalla_autoincrement)

        texto.text = title
        texto.visibility = View.VISIBLE



        val animator = ValueAnimator.ofInt(valor1, valor2)
        animator.duration = 2000
        animator.addUpdateListener { animation -> texto.text = animation.animatedValue.toString() }
        animator.start()


        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        return builder.create()
    }


    fun startCountAnimation(valueOld: Int, valueNew: Int) {
        val animator = ValueAnimator.ofInt(valueOld, valueNew)
        animator.duration = 2000
        animator.addUpdateListener { animation -> medalla_autoincrement.setText(animation.animatedValue.toString()) }
        animator.start()
    }

}