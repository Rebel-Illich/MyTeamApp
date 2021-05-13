package com.hunter.myteams.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class TeamDb: RoomDatabase() {
}