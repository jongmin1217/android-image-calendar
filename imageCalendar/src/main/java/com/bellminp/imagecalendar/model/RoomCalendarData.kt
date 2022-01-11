package com.bellminp.imagecalendar.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calendar")
data class RoomCalendarData(
    @PrimaryKey(autoGenerate = true)
    val id : Long
)