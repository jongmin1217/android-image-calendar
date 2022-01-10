package com.bellminp.imagecalendar.model

data class CalendarData(
    val id : Int,
    val visible : Boolean,
    val text : String?,
    val image : Any?,
    val imageVisible : Boolean,
    val circleImage : Boolean
)