package com.bellminp.imagecalendar.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bellminp.imagecalendar.base.MvvmCustomViewModel

class ImageCalendarViewModel : MvvmCustomViewModel<ImageCalendarViewState> {
    val title = MutableLiveData<String>()

}