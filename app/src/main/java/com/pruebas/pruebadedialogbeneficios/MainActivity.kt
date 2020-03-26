package com.pruebas.pruebadedialogbeneficios

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        stepProgressBar.apply {
            max = 6
            step = (3 - 1)
            stepDoneColor = R.color.pintado
            stepUndoneColor = R.color.bloqueado
        }

        bannerInformation.apply {
            setTitle("HOLA")
            setTitleBold(true)
            setTitleColor(resources.getColor(R.color.white))
            setTitleSize(16F)
            setDescription("ESTA ES UNA DESCRIPCION DE PRUEBA")
            setDescriptionColor(resources.getColor(R.color.white))
            setDescriptionSize(14F)
            setDescriptionBold(false)
            setCornerRadius(6f)
            setPicImage(resources.getDrawable(R.drawable.consultora_default))
            setImageVisible(true)
            setButtonVisible(true)
            setGradient(resources.getDrawable(R.drawable.shape_nueva))
        }


        btn_test.setOnClickListener {

            val dialogBuilder = FullScreenDialog.Builder(this)
                .withTitle("")
                .withMessage("")
                .withIcon(R.drawable.ic_perla)
                .withScreenDismiss(true)
                .setTime(5000)


            dialogBuilder.withAnimation(
                resources, FullScreenDialog.SIMPLE_ANIMATION,
                ContextCompat.getColor(this, R.color.dorado),
                ContextCompat.getColor(this, R.color.default_magenta)
            )

            dialogBuilder.show()

            /*
            val old = 300
            val new = 535
            val newDialogCustom = NewDialogCustom(old, new)
            supportFragmentManager?.let { it1 -> newDialogCustom.show(it1, "") }
            */
        }

        val sizeCantidad = stepProgressBar.max
        val pintadas = (stepProgressBar.step) + 1

        textStep.text = "Vas $pintadas de $sizeCantidad pedidos este periodo"
        textF.setText(R.string.text_fa)

        startCountAnimation(300, 350)


    }

    private fun startCountAnimation(valueOld: Int, valueNew: Int) {
        val animator = ValueAnimator.ofInt(valueOld, valueNew)
        animator.duration = 1500
        animator.addUpdateListener { animation ->
            textAuto.text = animation.animatedValue.toString()
        }
        animator.start()
    }

}

