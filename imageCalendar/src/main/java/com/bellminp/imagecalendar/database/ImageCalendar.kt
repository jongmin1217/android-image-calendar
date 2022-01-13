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

class ImageCalendar(context : Context) {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var mInstance: ImageCalendar
        fun get(): ImageCalendar {
            return mInstance
        }
    }

    private val compositeDisposable = CompositeDisposable()

    private var database: AppDatabase = AppDatabase.getInstance(context)

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun deleteCalendar(id : Long){
        addDisposable(database.roomCalendarDataDao().delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

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
            if(image is String) image else null,
            textColor
        )


        addDisposable(database.roomCalendarDataDao().insertCalendar(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }) {
                Log.d("timber","insert error $it")
            })
    }
}