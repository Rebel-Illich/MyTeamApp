package com.hunter.myteams.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.hunter.myteams.R
import kotlinx.android.synthetic.main.layout_filter_buttons.view.*


class FilterButtonsView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0
): LinearLayout(context,attrs,defStyleAttr) {

    private var buttonsListener: FilterSelectListener? = null

    interface FilterSelectListener {
        fun onWalkClick()
        fun onTimeClick()
    }

    init {
        initialisationLayout(context, attrs, defStyleAttr)
    }

    private fun initialisationLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_filter_buttons, this, true)

        filter_walk_button?.setOnClickListener { buttonsListener?.onWalkClick() }
        filter_time_button?.setOnClickListener { buttonsListener?.onTimeClick() }

    }

    fun setCaptionText(text: String) {
        caption.text = text
    }

    fun setTimeText(text: String) {
        time_text.text = text
    }

    fun setFilterSelectListener(filterSelectListener: FilterSelectListener) {
        buttonsListener = filterSelectListener
    }

    fun setImageActivity(res:Int){
        imageStep?.setImageResource(res)
    }
}





