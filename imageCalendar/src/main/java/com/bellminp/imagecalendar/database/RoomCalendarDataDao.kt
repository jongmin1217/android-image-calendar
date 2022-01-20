package com.bellminp.imagecalendar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bellminp.imagecalendar.model.DeleteCalendarData
import com.bellminp.imagecalendar.model.RoomCalendarData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface RoomCalendarDataDao {

    @Query("SELECT * FROM bm_calendar WHERE tag = :tag AND year = :year AND month = :month")
    fun getCalendar(tag: String, year: Int, month: Int): Observable<List<RoomCalendarData>>

    @Query("SELECT * FROM bm_calendar WHERE tag = :tag AND year = :year AND month = :month AND day = :day")
    fun getCalendarDay(tag: String, year: Int, month: Int, day: Int): Single<List<RoomCalendarData>>

    @Insert
    fun insertCalendar(roomCalendarData: List<RoomCalendarData>): Completable

    @Update
    fun updateCalendar(roomCalendarData: RoomCalendarData): Completable

    @Query("DELETE FROM bm_calendar WHERE tag = :tag AND year = :year AND month = :month AND day = :day")
    fun delete(tag: String, year: Int, month: Int, day: Int): Completable
}