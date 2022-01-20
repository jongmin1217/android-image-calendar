package com.bellminp.imagecalendar.model

import androidx.room.Entity
import java.io.Serializable

data class DeleteCalendarData(
    val tag : String,
    val year : Int,
    val month : Int,
    val day : Int
) : Serializable