package com.hunter.myteams.fragmentAllTeams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hunter.myteams.MyTeamApp

class AllTeamFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AllTeamViewModel(MyTeamApp.instance) as T
    }

}