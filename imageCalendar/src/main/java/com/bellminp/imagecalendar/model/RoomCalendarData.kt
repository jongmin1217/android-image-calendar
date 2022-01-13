package com.bellminp.imagecalendar.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bm_calendar")
data class RoomCalendarData(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val year : Int,
    val month : Int,
    val day : Int,
    val tag : String,
    val regDate : Long,
    val imageUrl : String? = null,
    val textColor : Int? = null
)