package com.bellminp.imagecalendar.listener

interface DeleteCalendarCallback {
    fun onSuccess()
    fun onFail(error : Throwable)
}