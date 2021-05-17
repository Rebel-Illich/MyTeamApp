package com.hunter.myteams.db

data class CurrentActivityFilter(

    val name: String
){
    val id: Int = 0
}

data class CurrentTimeFilter(
    val name: String
) {
    var id: Int = 0
}
