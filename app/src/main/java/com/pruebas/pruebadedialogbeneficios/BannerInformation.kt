package com.pruebas.pruebadedialogbeneficios

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.banner_information.view.*

class BannerInformation @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    var bannerListener: OnBannerListener? = null
    internal var placeholder: Drawable? = null
    internal var cornerRadius: Float =
        context.resources.getDimension(R.dimen.banner_information_default_radius)

    internal var titleText: String? = null
    internal var titleColor: Int = ContextCompat.getColor(context, R.color.black)
    internal var titleSize: Float =
        context.resources.getDimension(R.dimen.banner_information_default_title_size)
    internal var titleBold: Boolean = true

    internal var descriptionText: String? = null
    internal var descriptionColor: Int = ContextCompat.getColor(context, R.color.black)
    internal var descriptionSize: Float =
        context.resources.getDimension(R.dimen.banner_information_default_subtitle_size)
    internal var descriptionBold: Boolean = false

    internal var titleButton: String? = null
    internal var titleButtonColor: Int = ContextCompat.getColor(context, R.color.white)

    init {
        inflate()
        setupAttrs()
        setupUI()
    }

    private fun inflate() {
        inflate(context, R.layout.banner_information, this)
    }

    private fun setupAttrs() {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.BannerInformation, defStyleAttr, 0)
        try {
            placeholder =
                ta.getDrawable(R.styleable.BannerInformation_banner_information_placeholder)
            cornerRadius = ta.getDimension(
                R.styleable.BannerInformation_banner_information_card_corner,
                cornerRadius
            )
            titleText = ta.getString(R.styleable.BannerInformation_banner_information_title)
            titleColor = ta.getColor(
                R.styleable.BannerInformation_banner_information_title_color,
                titleColor
            )
            titleBold = ta.getBoolean(
                R.styleable.BannerInformation_banner_information_title_bold,
                titleBold
            )
            titleSize = ta.getDimension(
                R.styleable.BannerInformation_banner_information_title_size,
                titleSize
            )
            descriptionText =
                ta.getString(R.styleable.BannerInformation_banner_information_subtitle)
            descriptionColor = ta.getColor(
                R.styleable.BannerInformation_banner_information_title_color,
                descriptionColor
            )
            descriptionSize = ta.getDimension(
                R.styleable.BannerInformation_banner_information_subtitle_size,
                descriptionSize
            )
            descriptionBold = ta.getBoolean(
                R.styleable.BannerInformation_banner_information_subtitle_bold,
                descriptionBold
            )
            titleButton = ta.getString(R.styleable.BannerInformation_banner_information_titleButton)
            titleButtonColor = ta.getColor(
                R.styleable.BannerInformation_banner_information_titleButton_color,
                titleColor
            )

        } finally {
            ta.recycle()
        }
    }

    private fun setupUI() {
        setupListener()
    }

    private fun setupListener() {
        btnSee.setOnClickListener {
            bannerListener?.onButtonClick(it)
        }
    }

    fun setTitle(text: String?) {
        this.titleText = text
        setupUI()
    }

    fun getTitle(): String? {
        return this.titleText
    }

    fun setTitleColor(@ColorInt color: Int) {
        this.titleColor = color
        setupUI()
    }

    fun setTitleSize(dimension: Float) {
        this.titleSize = dimension
        setupUI()
    }

    fun setTitleBold(bold: Boolean) {
        this.titleBold = bold
        setupUI()
    }

    fun setDescription(text: String?) {
        this.descriptionText = text
        setupUI()
    }

    fun setDescriptionColor(@ColorInt color: Int) {
        this.descriptionColor = color
        setupUI()
    }

    fun setDescriptionColor(color: String) {
        var ColorDef = ContextCompat.getColor(context, R.color.white)
        if (!color.isEmpty()) {
            try {
                ColorDef = Color.parseColor(color)
            } catch (iae: IllegalArgumentException) {

            }
        }
        this.descriptionColor = ColorDef
        setupUI()
    }

    fun setDescriptionSize(dimension: Float) {
        this.descriptionSize = dimension
        setupUI()
    }

    fun setDescriptionBold(bold: Boolean) {
        this.descriptionBold = bold
        setupUI()
    }

    fun setPlaceholder(placeholder: Drawable) {
        this.placeholder = placeholder
    }

    fun setCornerRadius(radius: Float) {
        this.cornerRadius = radius
        setupUI()
    }

    fun setPicImage(background: Drawable?) {
        //Glide.with(imgContainer).clear(imgContainer)
        imgContainer.background = background
    }

    fun setImageVisible(visible: Boolean) {
        if (visible) {
            lnrContainer.visibility = View.VISIBLE
        } else {
            lnrContainer.visibility = View.GONE
        }
    }

    fun setButtonVisible(visible: Boolean) {
        if (visible) {
            btnSee.visibility = View.VISIBLE
        } else {
            btnSee.visibility = View.GONE
        }
    }

    fun setBackgroundImage(background: Drawable) {
        imgContainer.setImageDrawable(background)
    }

    fun setGradient(gradientDrawable: Drawable) {
        //Glide.with(backgroundContainer).clear(backgroundContainer)
        backgroundContainer.background = gradientDrawable
    }

    // INTERFACE

    interface OnBannerListener {
        fun onButtonClick(view: View)
    }

}