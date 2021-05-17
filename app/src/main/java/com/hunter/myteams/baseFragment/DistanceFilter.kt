package com.hunter.myteams.baseFragment

data class DistanceFilter(

    val id: Int,
    val name: String,
    val isChecked: Boolean,
    val filters: List<String>

)
