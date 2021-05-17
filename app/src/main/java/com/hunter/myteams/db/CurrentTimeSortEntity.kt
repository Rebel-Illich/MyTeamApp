package com.hunter.myteams.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_time")
data class СurrentTimeSortEntity(
    @ColumnInfo(name = "name")
    val name: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}