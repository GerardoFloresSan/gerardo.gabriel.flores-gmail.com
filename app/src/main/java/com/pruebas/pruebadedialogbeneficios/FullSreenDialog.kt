package com.pruebas.pruebadedialogbeneficios

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.text.Spanned
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.dialog_fullscreen.*


class FullScreenDialog(
    context: Context,
    private var icon: Int?,
    private var iconColor: Int?,
    private var iconAnimation: Boolean,
    private var titleLevel: String?,
    private var titleNamePerson: String?,
    private var title: String,
    private var titleHtml: Spanned?,
    private var titleAllCaps: Boolean,
    private var sizeTitle: Float,
    private var message: String,
    private var messageHtml: Spanned?,
    private var botonClose: Boolean,
    private var sizeMessage: Float,
    private var buttonMessage: String,
    private var buttonStyleDefault: Boolean,
    private var vanish: Boolean,
    private var action: String,
    private var screenDismiss: Boolean,
    private var scr: List<Int>?,
    private var resources: Resources?,
    private var type: Int,
    private var timeInMillis: Long,
    private var listener: FullScreenDialogListener?
) :
    Dialog(context, R.style.full_screen_dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_fullscreen)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        if (botonClose) {
            ivwDialogCloseGeneral.visibility = View.VISIBLE
        }

        icon?.let {
            ivDialog.setImageResource(it)
        }

        iconColor?.let {
            ivDialog.setColorFilter(ContextCompat.getColor(context, it))
        }

        if (iconAnimation) {
            ivDialog.visibility = View.GONE
        } else {
            ivDialog.visibility = View.VISIBLE
        }

        ivwDialogCloseGeneral.setOnClickListener {
            dismiss()
        }


        tvwNivel.text = titleLevel
        tvwNamePerson.text = titleNamePerson
        tvwTitle.text = if (title.isNotEmpty() || titleHtml.isNullOrEmpty()) title else titleHtml
        tvwTitle.textSize = sizeTitle
        tvwTitle.setAllCaps(titleAllCaps)
        tvwMessage.text =
            if (message.isNotEmpty() || messageHtml.isNullOrEmpty()) message else messageHtml
        tvwMessage.textSize = sizeMessage

        setOnKeyListener { arg0, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                listener?.onBackPressed(this@FullScreenDialog)
            }
            true
        }

        if (!buttonMessage.isEmpty()) {
            btnDialog.text = buttonMessage
            btnDialog.visibility = View.VISIBLE

            /*
            if (buttonStyleDefault) {
                btnDialog.strokeWidth = context.resources.getDimensionPixelSize(R.dimen.border_none)
                btnDialog.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.button_background_tint)
                btnDialog.setTextColor(AppCompatResources.getColorStateList(context, R.color.button_text))
            } else {
                btnDialog.strokeColor = AppCompatResources.getColorStateList(context, R.color.button_background_tint_black)
                btnDialog.strokeWidth = context.resources.getDimensionPixelSize(R.dimen.border_size)
                btnDialog.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.transparent)
                btnDialog.setTextColor(AppCompatResources.getColorStateList(context, R.color.button_text_black))
            }*/

            btnDialog.setOnClickListener {
                listener?.onClickAceptar(this)
            }
        }


        if (!action.isEmpty()) {
            tvwAction.paintFlags = tvwAction.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            tvwAction.visibility = View.VISIBLE
            tvwAction.text = action
            tvwAction.setOnClickListener {
                listener?.onClickAction(this)
            }
        }

        if (screenDismiss) {
            frlScreen.setOnClickListener {
                dismiss()
            }
        }

        @Suppress("CAST_NEVER_SUCCEEDS")
        scr?.let { images ->
            resources?.let {
                content_animation_level.postDelayed({
                    if (type == CUSTOM_ANIMATION) {
                        FestivityAnimationUtil.imageConfetti(it, lnlContainer, images)
                    } else if (type == EXPLOSION_ANIMATION) {
                        FestivityAnimationUtil.getCommonConfettiExplosion(
                            intArrayOf(
                                images[1],
                                images[0]
                            ), lnlContainer
                        )

                    } else {
                        if (images.size >= 2) {
                            FestivityAnimationUtil.getCommonConfetti(
                                images[0],
                                images[1],
                                it, lnlContainer
                            )
                        } else {
                            Log.wtf("YAYO", "Se debe añadir al menos dos colores para mostrar")
                        }
                    }
                }, 300)
                if (vanish)
                    finishDialog()
            }
        } ?: run {
            if (vanish)
                finishDialog()
        }
    }

    private fun finishDialog() {
        Handler().postDelayed({
            val fadeOut = AlphaAnimation(1f, 0f)
            fadeOut.duration = 1000
            frlScreen.animation = fadeOut
            frlScreen.startAnimation(fadeOut)
            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    listener?.onDismiss()
                    dismiss()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
        }, timeInMillis)
    }

    class Builder(private var context: Context) {

        private var icon: Int? = null
        private var iconColor: Int? = null
        private var iconAnimation: Boolean = false
        private var titleLevel: String = ""
        private var titleNamePerson: String = ""
        private var title: String = ""
        private var titleHtml: Spanned? = null
        private var titleAllCaps: Boolean = true
        private var message: String = ""
        private var messageHtml: Spanned? = null
        private var buttonMessage: String = ""
        private var action: String = ""
        private var listener: FullScreenDialogListener? = null
        private var scr: List<Int>? = null
        private var resources: Resources? = null
        private var type: Int = SIMPLE_ANIMATION
        private var screenDismiss = false
        private var sizeTitle: Float = 20f
        private var sizeMessage: Float = 18f
        private var buttonStyleDefault: Boolean = true
        private var vanish: Boolean = true
        private var botonClose: Boolean = false
        private var timeInMillis: Long = 6500

        fun withButtonStyleDefault(default: Boolean = true): Builder {
            this.buttonStyleDefault = default
            return this
        }

        fun withButtonClose(default: Boolean = false): Builder {
            this.botonClose = default
            return this
        }

        fun withVanish(default: Boolean = true): Builder {
            this.vanish = default
            return this
        }

        fun withIcon(icon: Int): Builder {
            this.icon = icon
            return this
        }

        fun withIconColor(color: Int?): Builder {
            this.iconColor = color
            return this
        }

        fun withIconAnimation(): Builder {
            this.iconAnimation = true
            return this
        }

        fun withTitleLevel(titleLevel: String): Builder {
            this.titleLevel = titleLevel
            return this
        }

        fun withTitleNamePerson(titleNamePerson: String): Builder {
            this.titleNamePerson = titleNamePerson
            return this
        }

        fun withTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun withTitle(title: String, size: Float): Builder {
            this.sizeTitle = size
            this.title = title
            return this
        }

        fun withTitleHtml(titleHtml: Spanned, size: Float): Builder {
            this.sizeTitle = size
            this.titleHtml = titleHtml
            return this
        }

        fun withMessage(message: String, size: Float): Builder {
            this.sizeMessage = size
            this.message = message
            return this
        }

        fun withMessageHtml(messageHtml: Spanned, size: Float): Builder {
            this.sizeMessage = size
            this.messageHtml = messageHtml
            return this
        }

        fun withMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun withButtonMessage(buttonMessage: String): Builder {
            this.buttonMessage = buttonMessage
            return this
        }

        fun setOnItemClick(listener: FullScreenDialogListener): Builder {
            this.listener = listener
            return this
        }

        fun withAction(text: String): Builder {
            this.action = text
            return this
        }

        fun withAnimation(resources: Resources, type: Int, vararg scr: Int): Builder {
            this.scr = scr.toList()
            this.resources = resources
            this.type = type
            return this
        }

        fun withScreenDismiss(screenDismiss: Boolean): Builder {
            this.screenDismiss = screenDismiss
            return this
        }

        fun setTime(timeInMillis: Long): Builder {
            this.timeInMillis = timeInMillis
            return this
        }

        fun show() = FullScreenDialog(
            context,
            icon,
            iconColor,
            iconAnimation,
            titleLevel,
            titleNamePerson,
            title,
            titleHtml,
            titleAllCaps,
            sizeTitle,
            message,
            messageHtml,
            botonClose,
            sizeMessage,
            buttonMessage,
            buttonStyleDefault,
            vanish,
            action,
            screenDismiss,
            scr,
            resources,
            type,
            timeInMillis,
            listener
        ).show()
    }

    interface FullScreenDialogListener {
        fun onClickAceptar(dialog: FullScreenDialog)
        fun onClickAction(dialog: FullScreenDialog)
        fun onDismiss()
        fun onBackPressed(dialog: FullScreenDialog) {
            dialog.dismiss()
        }
    }

    companion object {
        const val SIMPLE_ANIMATION = 1
        const val CUSTOM_ANIMATION = 2
        const val EXPLOSION_ANIMATION = 3
    }
}