package com.bellminp.imagecalendar.view

import com.bellminp.imagecalendar.base.MvvmCustomViewState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageCalendarViewState(
    val title : String
) : MvvmCustomViewState