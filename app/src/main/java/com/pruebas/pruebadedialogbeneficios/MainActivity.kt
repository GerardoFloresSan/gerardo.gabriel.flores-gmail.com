package com.pruebas.pruebadedialogbeneficios

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            val newWelcomeNewDialogCustom = WelcomeNewDialogCustom()
            supportFragmentManager?.let { it1 -> newWelcomeNewDialogCustom.show(it1, "") }

            /*
            //TODO animacion para cuando subes o bajas de nivel
            val dialogBuilder = FullScreenDialog.Builder(this)
                .withTitleYourLevel("Tu Nivel:")
                .withTitleLevel("Perla")
                .withTitleNamePerson("¡Muy Bien Gerardo Gabriel \\nFlores Sanchez!")
                .withTitle("Te mantuviste en Nivel Perla")

                .withMessage("Recuerda que mejorando tu nivel\\n tiene más y mejores beneficios")
                .withIcon(R.drawable.ic_perla)
                .withScreenDismiss(true)
                .setTime(5000)

            dialogBuilder.withAnimation(
                resources, FullScreenDialog.EXPLOSION_ANIMATION_LEVEL,
                ContextCompat.getColor(this, R.color.nuevo_color_animation),
                ContextCompat.getColor(this, R.color.nuevo_color_animation_two),
                ContextCompat.getColor(this, R.color.nuevo_color_animation_threet)
            )

            dialogBuilder.show()
               */

            /*
            //TODO animacion para ver cuantas medallas tienes
            val dialogBuilderIcon = FullScreenDialog.Builder(this)
                .withIcon(R.drawable.ic_medalla)
                .withTitleIcon("Ya tienes")
                .withValueIcon("350")
                .withSubTitleIcon("medallas")
                .withDescriptionIcon("Canjéalas por productos y\\n experiencias inolvidables para ti y \\ntu familia")
                .withScreenDismiss(true)
                .setTime(5000)


            dialogBuilderIcon.withAnimation(
                resources, FullScreenDialog.EXPLOSION_ANIMATION_ICON,
                ContextCompat.getColor(this, R.color.nuevo_color_animation),
                ContextCompat.getColor(this, R.color.nuevo_color_animation_two),
                ContextCompat.getColor(this, R.color.nuevo_color_animation_threet)
            )

            dialogBuilderIcon.show()
            */



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

