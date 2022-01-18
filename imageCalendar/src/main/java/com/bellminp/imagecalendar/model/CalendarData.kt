package com.bellminp.imagecalendar.model

data class CalendarData(
    val id : Int,
    val visible : Boolean = false,
    val year : Int = 0,
    val month : Int = 0,
    val day : Int = 0,
    val image : Any? = null,
    val imageVisible : Boolean = false,
    val circleImage : Boolean = false
){
    fun dayText() : String?{
        return if(day == 0) null
        else day.toString()
    }
}