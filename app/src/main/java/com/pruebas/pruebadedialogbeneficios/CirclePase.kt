package com.pruebas.pruebadedialogbeneficios

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout


class CirclePase(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {


    val colorPorDefecto = "#FFD9D5"
    val cuantosCirculos = 6
    val colorActivado = "#B66FA6"

    init {
        View.inflate(context, R.layout.circle_automatic, this)
    }

    fun autoCreatedCircle(cantidad : Int?){

    }
    fun setColorIndicador(resIdColor: Int?) {

    }


    override fun onDraw(canvas: Canvas) {
    val x = width
    val y = height
    val radius: Int = 100
    val paint = Paint()
    paint.setStyle(Paint.Style.FILL)

    paint.setColor(Color.parseColor(colorPorDefecto))

    canvas.drawCircle((x / 2).toFloat(), (y / 2).toFloat(), radius.toFloat(), paint)

    }

}
