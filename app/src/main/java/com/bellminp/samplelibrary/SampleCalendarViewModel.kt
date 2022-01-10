package com.bellminp.samplelibrary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleCalendarViewModel : ViewModel() {
    val text = MutableLiveData<String>()
}