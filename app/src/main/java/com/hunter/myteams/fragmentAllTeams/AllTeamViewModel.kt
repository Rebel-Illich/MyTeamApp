package com.hunter.myteams.fragmentAllTeams

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.hunter.myteams.MyTeamApp
import com.hunter.myteams.baseFragment.BaseViewModel
import com.hunter.myteams.network.ApiRequest
import com.hunter.myteams.repositories.AllTeamRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")

class AllTeamViewModel(instance: MyTeamApp) : BaseViewModel(instance) {

    var api: ApiRequest = instance.api

    var allTeamRepository: AllTeamRepository = AllTeamRepository(api)

    private val allTeams: MutableLiveData<List<Result>> =
        MutableLiveData<List<Result>>()


    fun getAllTeams(): MutableLiveData<List<Result>> {
        return allTeams
    }


    fun updateAllTeams() {

        allTeamRepository.getAllTeamApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { teamsAll -> allTeams.postValue(teamsAll.results!!) },
                {  error -> Log.e(error.toString(), "update failed") }
            )
    }
}



