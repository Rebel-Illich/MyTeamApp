package com.hunter.myteams.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hunter.myteams.utils.Converters

@Entity(tableName = "current_activity")
data class CurrentActivitySortEntity(

    @ColumnInfo(name = "name")
    val name: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

