package com.hunter.myteams.db

import com.hunter.myteams.baseFragment.DistanceFilter

interface FilterTeamRepository {

    fun save(distanceFilter: DistanceFilter)

    fun saveAllFilters(list: List<String>)

}