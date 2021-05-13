package com.hunter.myteams.customview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialogStepsViewModel: ViewModel() {
    val name = MutableLiveData<String>()

    fun sendName(text: String) {
        name.value = text
    }
}