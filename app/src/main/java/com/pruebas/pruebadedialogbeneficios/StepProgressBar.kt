package com.pruebas.pruebadedialogbeneficios

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.CallSuper
import androidx.core.content.ContextCompat

class StepProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val defaultHeight =
        resources.getDimensionPixelSize(R.dimen.step_progressbar_default_height)

    private var needInitial = true

    var max: Int = DEFAULT_MAX
        set(value) {
            field = value
            makeStepView()
        }

    var step: Int = DEFAULT_STEP
        set(value) {
            field = value
            makeStepView()
        }

    var stepDoneColor = 0

    var stepUndoneColor = 0

    var stepMargin = resources.getDimensionPixelSize(R.dimen.step_progressbar_default_margin)
        set(value) {
            field = value
            makeStepView()
        }


    init {
        orientation = HORIZONTAL
        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(
                    attrs,
                    R.styleable.StepProgressBar, defStyleAttr, 0
                )
            max = typedArray.getInt(R.styleable.StepProgressBar_max, max)
            step = typedArray.getInt(R.styleable.StepProgressBar_step, step)
            stepDoneColor =
                typedArray.getColor(R.styleable.StepProgressBar_stepDoneColor, stepDoneColor)
            stepUndoneColor =
                typedArray.getColor(R.styleable.StepProgressBar_stepUndoneColor, stepUndoneColor)
            stepMargin =
                typedArray.getDimensionPixelSize(R.styleable.StepProgressBar_stepMargin, stepMargin)

            typedArray.recycle()
        }
    }

    @CallSuper
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val height = getDefaultHeight(defaultHeight, heightMeasureSpec)
        super.onMeasure(width, height)
        if (needInitial) {
            needInitial = false
            makeStepView(width, height)
        }
    }

    private fun getDefaultHeight(size: Int, measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        return when (specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.UNSPECIFIED, MeasureSpec.AT_MOST -> size
            else -> size
        }
    }

    private fun makeStepView(width: Int = getWidth(), height: Int = getHeight()) {
        if (needInitial) {
            return
        }

        removeAllViewsInLayout()

        val undoneStepCount = max

        repeat(undoneStepCount) {
            addUndoneView(it, height)
        }
    }

    private fun addUndoneView(position: Int, hw: Int) {
        addView(View(context).apply {
            layoutParams = LayoutParams(hw, hw).apply { leftMargin = stepMargin }

            background = ContextCompat.getDrawable(context, R.drawable.progress_circle)
            background.setColorFilter(
                if (position <= step) ContextCompat.getColor(
                    context,
                    stepDoneColor
                ) else ContextCompat.getColor(context, stepUndoneColor), PorterDuff.Mode.SRC_ATOP
            )
            backgroundTintList = if (position <= step) ContextCompat.getColorStateList(
                context,
                stepDoneColor
            ) else ContextCompat.getColorStateList(context, stepUndoneColor)

        })
    }

    companion object {
        private const val DEFAULT_MAX = 10
        private const val DEFAULT_STEP = 0
    }

}