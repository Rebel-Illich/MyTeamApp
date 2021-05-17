package com.hunter.myteams.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentActivitySortEntity: CurrentActivitySortEntity) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentTimeSortEntity: СurrentTimeSortEntity) : Completable

    @Query("SELECT * FROM `current_time`")
    fun getTimeEntity(): Observable<List<СurrentTimeSortEntity>>

    @Query("SELECT * FROM `current_activity`")
    fun getActivityEntity(): Observable<List<CurrentActivitySortEntity>>

}