package com.hunter.myteams.repositories.mycompany

import com.hunter.myteams.db.CurrentActivityFilter
import com.hunter.myteams.db.CurrentActivitySortEntity
import com.hunter.myteams.db.CurrentTimeFilter
import com.hunter.myteams.db.СurrentTimeSortEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MyCompanyRepository {

    fun getCurrentActivityFilter(): Observable<List<CurrentActivitySortEntity>>

    fun getCurrentTimeFilter(): Observable<List<СurrentTimeSortEntity>>

    fun insert(currentActivityFilter: CurrentActivityFilter) : Completable

    fun insert(currentTimeFilter: CurrentTimeFilter) : Completable
}