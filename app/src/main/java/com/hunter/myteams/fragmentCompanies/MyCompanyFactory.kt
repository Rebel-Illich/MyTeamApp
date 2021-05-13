package com.hunter.myteams.fragmentCompanies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hunter.myteams.MyTeamApp

class MyCompanyFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyCompanyViewModel(MyTeamApp.instance) as T

    }
}
