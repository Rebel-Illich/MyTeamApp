package com.hunter.myteams.fragmentAllTeams

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hunter.myteams.MyTeamApp
import com.hunter.myteams.R
import com.hunter.myteams.baseFragment.BaseFragment
import com.hunter.myteams.fragmentAllTeams.adapter.AllTeamsListAdapter

class AllTeamFragment : BaseFragment() {

    var allTeamsList: MutableList<Result> = mutableListOf()

    private var viewModel: AllTeamViewModel = AllTeamViewModel(MyTeamApp.instance)
    private var allTeamViewModel: AllTeamViewModel = AllTeamViewModel(
        MyTeamApp.instance)
    var recyclerAdapter = AllTeamsListAdapter(allTeamsList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel= ViewModelProvider(
            requireActivity(),
            AllTeamFactory()
        )
            .get(viewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_teams_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerAllTeamsList)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = recyclerAdapter


        viewModel.getAllTeams().observe(viewLifecycleOwner) { a ->
            allTeamsList.clear()
            allTeamsList.addAll(a)
            recyclerAdapter.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.updateAllTeams()
    }

}
