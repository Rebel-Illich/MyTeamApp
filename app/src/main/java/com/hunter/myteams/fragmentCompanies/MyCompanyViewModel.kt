package com.hunter.myteams.fragmentCompanies

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hunter.myteams.MyTeamApp
import com.hunter.myteams.baseFragment.BaseViewModel
import com.hunter.myteams.customview.DialogDistanceFragment
import com.hunter.myteams.customview.DialogTimeFragment
import com.hunter.myteams.db.*
import com.hunter.myteams.network.ApiRequest
import com.hunter.myteams.repositories.mycompany.MyCompanyRepositoryImpl
import com.hunter.myteams.states.AddFilterState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


private const val TAG: String = "MyCompanyViewModel"

class MyCompanyViewModel(instance: MyTeamApp) : BaseViewModel(instance) {

    var api: ApiRequest = instance.api

    private val _timeFilterLd = MutableLiveData<СurrentTimeSortEntity>()
    val timeFilterLd: LiveData<СurrentTimeSortEntity>
        get() = _timeFilterLd

    private val _distanceFilterLd = MutableLiveData<CurrentActivitySortEntity>()
    val distanceFilterLd: LiveData<CurrentActivitySortEntity>
        get() = _distanceFilterLd

    val addDone: MutableLiveData<AddFilterState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    private val myCompaniesRepositoryImpl: MyCompanyRepositoryImpl =
        MyCompanyRepositoryImpl(api, TeamDb.getInstance(null).filterDao())

    private val myCompanies: MutableLiveData<List<Result>> = MutableLiveData<List<Result>>()

    fun getCompanies(): MutableLiveData<List<Result>> {
        return myCompanies
    }


    @SuppressLint("CheckResult")
    fun updateCompanies() {
        myCompaniesRepositoryImpl.getCurrentTimeFilter()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                if (result.isEmpty()) {
                    val defaultTimeName = DialogTimeFragment.ViewTimeState.TODAY.name
                    val defaultDistanceName = DialogDistanceFragment.ViewState.WALK.name
                    _timeFilterLd.value = СurrentTimeSortEntity(defaultTimeName)
                    _distanceFilterLd.value = CurrentActivitySortEntity(defaultDistanceName)
                    loadCompanies(defaultTimeName, defaultDistanceName)
                } else {
                    val last = result.last()
                    val lastDist = result.last()
                    _timeFilterLd.value = last
                    loadCompanies(last.name, lastDist.name)
                }
            }, { error ->
                Log.e(TAG, "MyCompanyViewModel: ", error)
            })

        myCompaniesRepositoryImpl.getCurrentActivityFilter()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                if (result.isEmpty()) {
                    val defaultTimeName = DialogTimeFragment.ViewTimeState.TODAY.name
                    val defaultDistanceName = DialogDistanceFragment.ViewState.WALK.name
                    _timeFilterLd.value = СurrentTimeSortEntity(defaultTimeName)
                    _distanceFilterLd.value = CurrentActivitySortEntity(defaultDistanceName)
                    loadCompanies(defaultTimeName, defaultDistanceName)
                } else {
                    val last = result.last()
                    val lastDist = result.last()
                    _distanceFilterLd.value = lastDist
                    loadCompanies(last.name, lastDist.name)
                }
            }, { error ->
                Log.e(TAG, "MyCompanyViewModel: ", error)
            })
    }


    @SuppressLint("CheckResult")
    private fun loadCompanies(timeFilter: String, distanceFilter: String) {
        myCompanies.value = emptyList()
        myCompaniesRepositoryImpl.getMyCompanyApi(timeFilter, distanceFilter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { all -> myCompanies.postValue(all.results) },
                { t -> Log.d("TAG", "updateCompanies: $t") }
            )
    }

    fun insert(currentActivityFilter: CurrentActivityFilter) {
        val subscription = myCompaniesRepositoryImpl
            .insert(currentActivityFilter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddFilterState.Success
                    updateCompanies()
                },
                {
                    addDone.value = AddFilterState.Error("Error activity")
                }
            )
        subscriptions.add(subscription)
    }

    fun insert(currentTimeFilter: CurrentTimeFilter) {
        val subscription = myCompaniesRepositoryImpl
            .insert(currentTimeFilter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddFilterState.Success
                    updateCompanies()
                },
                {
                    addDone.value = AddFilterState.Error("Error time")
                }
            )

        subscriptions.add(subscription)
    }
}


