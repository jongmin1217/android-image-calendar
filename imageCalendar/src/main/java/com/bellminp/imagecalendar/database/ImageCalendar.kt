package com.bellminp.imagecalendar.database

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import com.bellminp.imagecalendar.model.RoomCalendarData
import com.bellminp.imagecalendar.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.coroutines.coroutineContext

class ImageCalendar(private val context: Context) {
    private val compositeDisposable = CompositeDisposable()
    private var database: AppDatabase = AppDatabase.getInstance(context)

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun deleteCalendar(tag: String, year: Int,month: Int,day: Int){
        addDisposable(database.roomCalendarDataDao().delete(tag, year, month, day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("timber","delete ok")
            }) {
                Log.d("timber","delete error $it")
            })
    }


    fun addCalendar(year : Int, month : Int, day : Int, tag : String, image : Any, textColor : Int?=null){
        val data = RoomCalendarData(
            0,
            year,
            month,
            day,
            tag,
            Utils.getUnixTime(),
            when(image){
                is String -> image
                is Uri -> image.toString()
                is Int -> {
                    val iconName = context.resources.getResourceEntryName(image)
                    val resId = context.resources.getIdentifier(iconName,"drawable",context.packageName)
                    Log.d("timber","$resId")
                    resId.toString()
                }
                else -> null
            },
            textColor
        )


        addDisposable(database.roomCalendarDataDao().insertCalendar(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("timber","insert ok")
            }) {
                Log.d("timber","insert error $it")
            })
    }
}