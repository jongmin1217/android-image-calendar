package com.bellminp.imagecalendar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bellminp.imagecalendar.BuildConfig
import com.bellminp.imagecalendar.model.RoomCalendarData

@Database(entities = [RoomCalendarData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomCalendarDataDao() : RoomCalendarDataDao

    companion object{
        private lateinit var instance : AppDatabase

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bm_image_calendar"
                ).build()
            }
            return instance
        }
    }
}