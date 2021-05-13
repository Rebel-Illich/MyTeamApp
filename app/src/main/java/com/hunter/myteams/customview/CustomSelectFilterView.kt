package com.hunter.myteams.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hunter.myteams.R

class CustomSelectFilterView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var customDistanceView: LinearLayout

    private val categoryImageView: ImageView
    private var categoryTextView: TextView


    init {
        inflate(context, R.layout.custom_select_button_layout, this)


        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomSelectFilterView)
        customDistanceView = findViewById(R.id.layout_indicator_button)

        categoryImageView = findViewById(R.id.image_indicator_select)
        categoryTextView = findViewById(R.id.select_indicator_text)
        categoryImageView.setImageDrawable(attributes.getDrawable(R.styleable.CustomSelectFilterView_category_image))
        categoryTextView.text = attributes.getText(R.styleable.CustomSelectFilterView_category_name)
        attributes.recycle()

        isClickable = true
        isFocusable = true
    }


    fun setText(text: String) {
        categoryTextView.text = text
    }

    fun setCategoryImage(id: Int) {
        categoryImageView.setBackgroundResource(id)
    }

    fun setBackGround(id: Int) {
        customDistanceView.setBackgroundResource(id)
    }

}