package com.bellminp.imagecalendar.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageCalendarViewModel : ViewModel() {
    private fun <T : Any?> MutableLiveData<T>.default(initialValue: T?) = apply { setValue(initialValue) }

    val title = MutableLiveData<String>().default("")

}