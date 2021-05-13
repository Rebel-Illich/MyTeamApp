package com.hunter.myteams.fragmentMyTeam


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hunter.myteams.R
import com.hunter.myteams.baseFragment.BaseFragment


class MyTeamFragment(): BaseFragment(){

    private val TAG: String = "AppDebug"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_team_fragment, container, false)
    }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}




