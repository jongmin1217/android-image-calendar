package com.bellminp.imagecalendar.listener

import com.bellminp.imagecalendar.model.CalendarData

interface CalendarListener {
    fun calendarClick(calendarData: CalendarData)
}