package com.bellminp.imagecalendar.listener

import com.bellminp.imagecalendar.model.RoomCalendarData

interface InsertCalendarCallback {
    fun onSuccess()
    fun onFail(error : Throwable)
}