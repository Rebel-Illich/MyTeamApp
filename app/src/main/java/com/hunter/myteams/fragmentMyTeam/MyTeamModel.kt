package com.hunter.myteams.fragmentMyTeam

data class MyTeamModel(
    val metric: String,
    val period: String,
    val programId: String,
    val programName: String,
    val results: List<Any>,
    val teamId: String,
    val teamName: String,
    val userResult: Any
)