package com.hunter.myteams.network

import com.hunter.myteams.fragmentAllTeams.AllTeamModel
import com.hunter.myteams.fragmentCompanies.CompanyModel
import com.hunter.myteams.fragmentMyTeam.MyTeamModel
import io.reactivex.Single

import retrofit2.http.GET

interface ApiRequest {

    @GET("/v1/21c2ea7b-9c93-4a0f-8883-2706e07790fa")
    fun getAllTeamsDetails(): Single<AllTeamModel>

    @GET("/v1/5472d014-4ee8-4a52-a571-6d159beb1e0c")
    fun getMyCompanyDetails(): Single<CompanyModel>

    @GET("/v1/d1877370-b414-4ca0-8e7a-c55d8d6c4bac")
    fun getMyTeamDetails(): Single<MyTeamModel>
}