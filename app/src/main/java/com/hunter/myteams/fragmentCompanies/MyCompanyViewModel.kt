package com.hunter.myteams.fragmentCompanies

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hunter.myteams.LoadState
import com.hunter.myteams.MyTeamApp
import com.hunter.myteams.baseFragment.BaseViewModel
import com.hunter.myteams.network.ApiRequest
import com.hunter.myteams.repositories.MyCompanyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MyCompanyViewModel(instance: MyTeamApp) : BaseViewModel(instance) {
    var api: ApiRequest = instance.api

    private val myCompaniesRepository: MyCompanyRepository = MyCompanyRepository(api)

    private val myCompanies: MutableLiveData<List<Result>> = MutableLiveData<List<Result>>()

    fun getCompanies(): MutableLiveData<List<Result>> {
        return myCompanies
    }


    @SuppressLint("CheckResult")
    fun updateCompanies() {
        myCompaniesRepository.getMyCompanyApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { all -> myCompanies.postValue(all.results) },
                { t -> Log.d("TAG", "updateCompanies: $t") }
            )
    }
}


