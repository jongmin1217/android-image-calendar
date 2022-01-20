package com.bellminp.imagecalendar.database

import android.content.Context
import android.net.Uri
import android.util.Log
import com.bellminp.imagecalendar.listener.DeleteCalendarCallback
import com.bellminp.imagecalendar.listener.InsertCalendarCallback
import com.bellminp.imagecalendar.model.DeleteCalendarData
import com.bellminp.imagecalendar.model.InsertCalendarData
import com.bellminp.imagecalendar.model.RoomCalendarData
import com.bellminp.imagecalendar.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ImageCalendar(private val context: Context) {
    private val compositeDisposable = CompositeDisposable()
    private var database: AppDatabase = AppDatabase.getInstance(context)

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun deleteCalendar(deleteCalendarData: DeleteCalendarData, callback: DeleteCalendarCallback){
        deleteCalendar(listOf(deleteCalendarData),callback)
    }

    fun deleteCalendar(deleteCalendarData: List<DeleteCalendarData>, callback: DeleteCalendarCallback) {
        for(deleteData in deleteCalendarData){
            addDisposable(database.roomCalendarDataDao().delete(deleteData.tag,deleteData.year,deleteData.month,deleteData.day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onSuccess()
                }) {
                    callback.onFail(it)
                })
        }
    }


    fun addCalendar(insertCalendarData: InsertCalendarData, callback: InsertCalendarCallback) {
        addCalendar(listOf(insertCalendarData),callback)
    }
    
   fun addCalendar(insertCalendarData: List<InsertCalendarData>,callback: InsertCalendarCallback){
       val list = insertCalendarData.map {
           RoomCalendarData(
               0,
               it.year,
               it.month,
               it.day,
               it.tag,
               Utils.getUnixTime(),
               when (it.image) {
                   is String -> it.image
                   is Uri -> it.image.toString()
                   is Int -> {
                       val iconName = context.resources.getResourceEntryName(it.image)
                       val resId =
                           context.resources.getIdentifier(iconName, "drawable", context.packageName)
                       resId.toString()
                   }
                   else -> null
               },
               it.textColor
           )
       }

       addDisposable(database.roomCalendarDataDao().insertCalendar(list)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
               callback.onSuccess()
           }) {
               callback.onFail(it)
           })
   }




}
