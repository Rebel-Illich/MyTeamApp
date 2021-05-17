package com.hunter.myteams.customview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.hunter.myteams.R

class DialogTimeFragment: DialogFragment(), View.OnClickListener {

    private lateinit var indicatorTodayFilterView: CustomSelectFilterView
    private lateinit var indicatorWeekFilterView: CustomSelectFilterView
    private lateinit var indicatorMonthFilterView: CustomSelectFilterView

    private lateinit var saveButton: AppCompatButton
    private lateinit var cancelButton: AppCompatTextView
    private lateinit var customDistance: LinearLayout

    private var listenerTime: TimeFilterDialogListener? = null
    private var viewTimeState: ViewTimeState? = null

    enum class ViewTimeState{
        TODAY, DAYS, MONTH;
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog!!.window?.setBackgroundDrawableResource(R.drawable.indicator_corner)
        return inflater.inflate(R.layout.custom_dialog_time_frame_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners(view)

        indicatorTodayFilterView.setText("TODAY")
        indicatorTodayFilterView.setCategoryImage(R.drawable.ic_baseline_calendar_today_24)

        indicatorWeekFilterView.setText("7 DAYS")
        indicatorWeekFilterView.setCategoryImage(R.drawable.ic_baseline_filter_7_24)

        indicatorMonthFilterView.setText("MONTH")
        indicatorMonthFilterView.setCategoryImage(R.drawable.ic_baseline_calendar_view_month_24)

    }

    private fun setupClickListeners(view: View) {

        indicatorTodayFilterView = view.findViewById(R.id.today_time_indicator)
        indicatorWeekFilterView = view.findViewById(R.id.seven_days_time_indicator)
        indicatorMonthFilterView = view.findViewById(R.id.month_time_indicator)
        cancelButton = view.findViewById(R.id.btn_cancel_time)
        saveButton = view.findViewById(R.id.btn_save_time)


        saveButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)

        indicatorTodayFilterView.setOnClickListener(this)
        indicatorWeekFilterView.setOnClickListener(this)
        indicatorMonthFilterView.setOnClickListener(this)

    }


    companion object{
        private fun newInstance() =
            DialogTimeFragment()

        fun showTimeFilter(
            manager: FragmentManager,
            listenerTime: TimeFilterDialogListener
        ): DialogTimeFragment {
            return newInstance()
                .apply {
                this.listenerTime = listenerTime
                manager.beginTransaction()
                    .add(this, DialogTimeFragment::class.java.simpleName).commit()
            }
        }
    }

    interface TimeFilterDialogListener {
        fun onSaveTime(viewTimeState: ViewTimeState)
        fun onCancel()
        fun onDismiss()
    }

    override fun onClick(v: View?) {

        when (v) {
            cancelButton -> dismiss()
            saveButton -> {
                viewTimeState?.let {
                    listenerTime?.onSaveTime(it)
                }
                Toast.makeText(requireContext(), "Save Setting", Toast.LENGTH_SHORT).show()
                dismiss()
            }
            indicatorMonthFilterView -> {
                viewTimeState =
                    ViewTimeState.MONTH
                indicatorMonthFilterView.setBackGround(R.drawable.button_radius_corner)
            }
            indicatorWeekFilterView -> {
                viewTimeState =
                    ViewTimeState.DAYS
                indicatorWeekFilterView.setBackGround(R.drawable.button_radius_corner)
            }
            indicatorTodayFilterView -> {
                viewTimeState =
                    ViewTimeState.TODAY
                indicatorTodayFilterView.setBackGround(R.drawable.button_radius_corner)
            }

        }
    }
}
