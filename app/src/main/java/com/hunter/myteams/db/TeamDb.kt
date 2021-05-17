package com.hunter.myteams.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hunter.myteams.utils.Converters


@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TeamDb: RoomDatabase() {
    abstract val filterDao : TeamDao

    companion object {
        @Volatile
        private var instance: TeamDb? = null

        fun getInstance(
            context: Context
        ): TeamDb = instance
            ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it }
            }

        private fun buildDatabase(context: Context): TeamDb{
            return Room.databaseBuilder(
                context,
                TeamDb::class.java,
                "teamDB"
            ).fallbackToDestructiveMigration().build()
        }
    }
}