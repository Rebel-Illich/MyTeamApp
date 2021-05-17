package com.hunter.myteams

import android.app.Application
import com.hunter.myteams.db.TeamDb
import com.hunter.myteams.network.ApiRequest
import com.hunter.myteams.network.NetworkManager


class MyTeamApp: Application() {

    companion object{
        lateinit var instance: MyTeamApp
        private set
    }

    lateinit var database: TeamDb
        private set

    lateinit var api: ApiRequest
    private set

    override fun onCreate() {
        super.onCreate()

        instance = this
        api = NetworkManager.getRestApi()
        TeamDb.getInstance(this)
    }

}