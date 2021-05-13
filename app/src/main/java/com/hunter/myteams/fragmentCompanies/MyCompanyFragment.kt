package com.hunter.myteams.fragmentCompanies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hunter.myteams.MyTeamApp
import com.hunter.myteams.R
import com.hunter.myteams.baseFragment.BaseFragment
import com.hunter.myteams.fragmentCompanies.adapter.MyCompanyRecyclerListAdapter
import com.hunter.myteams.customview.DialogDistanceFragment
import com.hunter.myteams.customview.DialogTimeFragment
import com.hunter.myteams.customview.FilterButtonsView
import kotlinx.android.synthetic.main.my_company_fragment.*

private const val TAG: String = "MyCompanyFragment"

class MyCompanyFragment : BaseFragment(), DialogDistanceFragment.ActivityFilterDialogListener, DialogTimeFragment.TimeFilterDialogListener {
    var myCompaniesList: MutableList<Result> = mutableListOf()
    var recyclerAdapter = MyCompanyRecyclerListAdapter(myCompaniesList)

    private var distanceDialogFragment: DialogDistanceFragment? = null
    private var timeDialogFragment: DialogTimeFragment? = null

    private var myCompaniesViewModel: MyCompanyViewModel =
    MyCompanyViewModel(MyTeamApp.instance)


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myCompaniesViewModel = ViewModelProvider(
        requireActivity(), MyCompanyFactory()
        )
        .get(MyCompanyViewModel::class.java)
        }

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.my_company_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerAllTeamsList)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = recyclerAdapter

        myCompaniesViewModel
        .getCompanies()
        .observe(viewLifecycleOwner
        ) { c ->
            myCompaniesList.clear()
            myCompaniesList.addAll(c)
            recyclerAdapter.notifyDataSetChanged()

        }
            val selectB = object: FilterButtonsView.FilterSelectListener{
                override fun onWalkClick() {
                    Log.d("tag", "onActivityClick: ")
                    DialogDistanceFragment.showDistanceFilter(childFragmentManager,this@MyCompanyFragment)
                }

                override fun onTimeClick() {
                    Log.d("tag", "onTimeClick: ")

                    DialogTimeFragment.showTimeFilter(childFragmentManager, this@MyCompanyFragment)
                }
            }
            return filter_buttons_view.setFilterSelectListener(selectB)
        }

        override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: $this")
        myCompaniesViewModel.updateCompanies()
}

    override fun onSave(viewState: DialogDistanceFragment.ViewState) {
        when (viewState) {
            DialogDistanceFragment.ViewState.WALK -> {
                filter_buttons_view.setCaptionText("Steps")
                filter_buttons_view.setImageActivity(R.drawable.ic_baseline_step)}
            DialogDistanceFragment.ViewState.MAP -> {filter_buttons_view.setCaptionText("Distance")
                filter_buttons_view.setImageActivity(R.drawable.ic_baseline_location)
            }
        }
    }

    override fun onSaveTime(viewTimeState: DialogTimeFragment.ViewTimeState) {
       when(viewTimeState){
           DialogTimeFragment.ViewTimeState.TODAY -> filter_buttons_view.setTimeText("Today")
           DialogTimeFragment.ViewTimeState.DAYS -> filter_buttons_view.setTimeText("Week")
           DialogTimeFragment.ViewTimeState.MONTH -> filter_buttons_view.setTimeText("Month")
       }
    }

    override fun onCancel() {
        distanceDialogFragment?.dismiss()
        timeDialogFragment?.dismiss()
        onDismiss()
    }

    override fun onDismiss() {
        distanceDialogFragment = null
        timeDialogFragment = null
    }
}