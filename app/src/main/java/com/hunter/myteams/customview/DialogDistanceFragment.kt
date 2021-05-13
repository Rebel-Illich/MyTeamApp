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
import androidx.lifecycle.ViewModelProvider
import com.hunter.myteams.R


class DialogDistanceFragment: DialogFragment(), View.OnClickListener {

    private lateinit var filterStepView: CustomSelectFilterView
    private lateinit var filterMapView: CustomSelectFilterView
    private lateinit var customDistanceLinearLayout: LinearLayout

    private lateinit var saveButton: AppCompatButton
    private lateinit var cancelButton: AppCompatTextView

    private lateinit var viewModel: DialogStepsViewModel
    private var listener: ActivityFilterDialogListener? = null
    private var viewState: ViewState? = null
    enum class ViewState {
        WALK, MAP
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        dialog!!.window?.setBackgroundDrawableResource(R.drawable.indicator_corner);
        return inflater.inflate(R.layout.custom_dialog_distance_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(DialogStepsViewModel::class.java)

        filterStepView = view.findViewById(R.id.step_filter_indicator_button)
        filterMapView = view.findViewById(R.id.map_filter_indicator_button)

        cancelButton = view.findViewById(R.id.btnCancel)
        saveButton = view.findViewById(R.id.btnSave)

        customDistanceLinearLayout = view.findViewById(R.id.layout_indicator_button)

        setupClickListeners(view)
    }


    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    private fun setupClickListeners(view: View) {


        cancelButton.setOnClickListener(this)
        saveButton.setOnClickListener(this)

        filterStepView.setOnClickListener(this)
        filterMapView.setOnClickListener(this)
        filterStepView.setText("STEP")
        filterStepView.setCategoryImage(R.drawable.ic_baseline_step)
        filterMapView.setText("KM")
        filterMapView.setCategoryImage(R.drawable.ic_baseline_location)
    }


    companion object{
        private fun newInstance() =
            DialogDistanceFragment()

        fun showDistanceFilter(
            manager: FragmentManager,
            listener: ActivityFilterDialogListener
            ): DialogDistanceFragment {
                return newInstance()
                    .apply {
                    this.listener = listener
                    manager.beginTransaction()
                        .add(this, DialogDistanceFragment::class.java.simpleName).commit()
                }
            }
        }

        interface ActivityFilterDialogListener {
            fun onSave(viewState: ViewState)
            fun onCancel()
            fun onDismiss()
        }

    override fun onClick(v: View?) {
        when (v) {
            saveButton -> {
                viewState?.let {
                    listener?.onSave(it)
                }
                Toast.makeText(requireContext(), "save setting", Toast.LENGTH_SHORT).show()
                dismiss()
            }
            cancelButton -> dismiss()
            filterStepView -> {
                viewState =
                    ViewState.WALK
                filterStepView.setBackGround(R.drawable.button_radius_corner)
            }
            filterMapView  -> {
                viewState =
                    ViewState.MAP
                filterMapView .setBackGround(R.drawable.button_radius_corner)
            }
        }
    }
}


