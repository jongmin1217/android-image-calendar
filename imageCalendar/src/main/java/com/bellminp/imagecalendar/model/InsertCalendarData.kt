package com.bellminp.imagecalendar.model

data class InsertCalendarData(
    val year : Int,
    val month : Int,
    val day : Int,
    val tag : String,
    val image : Any? = null,
    val textColor : Int?=null
)