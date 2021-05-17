package com.hunter.myteams.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilter(entityTeamFilter: EntityTeamFilter)

}