package com.bellminp.imagecalendar.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ImageCalendarViewModel : ViewModel() {
    val title = MutableLiveData<String>()

    fun test(){
        Log.d("timber","${title.value}")
    }
}