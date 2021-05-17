package com.hunter.myteams.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hunter.myteams.utils.Converters

@Entity
class EntityTeamFilter (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "checked")
    val isChecked: Boolean,

    @TypeConverters(Converters::class)
    var filters: List<String>? = null

)