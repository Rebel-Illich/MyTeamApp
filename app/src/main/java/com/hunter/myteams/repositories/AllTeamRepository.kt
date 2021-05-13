package com.hunter.myteams.repositories

import com.hunter.myteams.network.ApiRequest

class AllTeamRepository(var api: ApiRequest) {

    fun getAllTeamApi() = api.getAllTeamsDetails()
}