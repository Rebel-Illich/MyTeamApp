package com.hunter.myteams.repositories.mycompany

import android.util.Log
import com.hunter.myteams.customview.DialogDistanceFragment
import com.hunter.myteams.customview.DialogTimeFragment
import com.hunter.myteams.db.*
import com.hunter.myteams.network.ApiRequest
import io.reactivex.Completable
import io.reactivex.Observable

private const val TAG: String = "MyCompanyRepositoryImpl"

class MyCompanyRepositoryImpl(var api: ApiRequest, val dataSource: TeamDao) : MyCompanyRepository {

    fun getMyCompanyApi(metricName: String, distanceName: String) =
        api.getMyCompanyDetails(metric = DialogDistanceFragment.ViewState.valueOf(distanceName.toUpperCase()),
            period = DialogTimeFragment.ViewTimeState.valueOf(metricName.toUpperCase())
        )

    override fun getCurrentActivityFilter(): Observable<List<CurrentActivitySortEntity>> {
        return dataSource.getActivityEntity()
    }

    override fun getCurrentTimeFilter(): Observable<List<СurrentTimeSortEntity>> {
        return dataSource.getTimeEntity()
    }

    override fun insert(currentActivityFilter: CurrentActivityFilter): Completable {

        val activityEntity = СurrentTimeSortEntity(currentActivityFilter.name)

        return dataSource.insert(activityEntity)
    }

    override fun insert(currentTimeFilter: CurrentTimeFilter): Completable {
        Log.d(TAG, "insert() called with: currentTimeFilter = [$currentTimeFilter]")
        val timeEntity = СurrentTimeSortEntity(currentTimeFilter.name)

        return dataSource.insert(timeEntity)
    }
}