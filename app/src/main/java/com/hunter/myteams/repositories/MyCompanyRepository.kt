package com.hunter.myteams.repositories

import com.hunter.myteams.network.ApiRequest

class MyCompanyRepository(var api: ApiRequest) {

    fun getMyCompanyApi() = api.getMyCompanyDetails()
}