package com.hunter.myteams.repositories

import android.app.Application
import com.hunter.myteams.baseFragment.DistanceFilter
import com.hunter.myteams.db.EntityTeamFilter
import com.hunter.myteams.db.FilterTeamRepository
import com.hunter.myteams.db.TeamDao
import com.hunter.myteams.db.TeamDb
import java.util.concurrent.Executors

class DistanceFilterRepository(application: Application) : FilterTeamRepository {

    private var distanceDao: TeamDao

    companion object {
        @Volatile
        private var INSTANCE: DistanceFilterRepository? = null

        fun getInstance(applicationContext: Application): DistanceFilterRepository {
            return INSTANCE ?: DistanceFilterRepository(applicationContext)
        }

    }

    init {
        val database: TeamDb = TeamDb.getInstance(application.applicationContext)
        distanceDao = database.filterDao
    }

    override fun save(distanceFilter: DistanceFilter) {

        Executors.newSingleThreadExecutor().execute {
            distanceDao.insertFilter(
                EntityTeamFilter(
                    distanceFilter.id,
                    distanceFilter.name,
                    distanceFilter.isChecked,
                    distanceFilter.filters
                )
            )
        }

    }

    override fun get(): DistanceFilter {
        TODO("Not yet implemented")
    }

    override fun saveAllFilters(list: List<String>) {
        TODO("Not yet implemented")
    }

}