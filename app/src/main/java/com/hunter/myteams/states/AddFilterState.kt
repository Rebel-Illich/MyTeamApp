package com.hunter.myteams.states

sealed class AddFilterState {
    object Success : AddFilterState()
    data class Error(val error: String) : AddFilterState()
}