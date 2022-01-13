package com.bellminp.imagecalendar.view

import android.content.res.Resources
import android.util.Log
import android.view.textclassifier.TextLanguage
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bellminp.imagecalendar.R
import com.bellminp.imagecalendar.database.AppDatabase
import com.bellminp.imagecalendar.model.CalendarData
import com.bellminp.imagecalendar.model.RoomCalendarData
import com.bellminp.imagecalendar.utils.SingleLiveEvent
import com.bellminp.imagecalendar.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ImageCalendarViewModel(private val database: AppDatabase) : ViewModel() {
    private fun <T : Any?> SingleLiveEvent<T>.default(initialValue: T) =
        apply { setValue(initialValue) }

    private val _changeMonth = SingleLiveEvent<Unit>()

    val changeMonth : LiveData<Unit> get() = _changeMonth

    val title = SingleLiveEvent<String>().default("")

    val calendarData = SingleLiveEvent<ArrayList<CalendarData>?>().default(null)

    private val compositeDisposable = CompositeDisposable()
    private var observableDisposable : Disposable? = null

    var calendarType = 0
    var language = String()
    var imageVisible = true
    var circleImage = false
    var defaultImage: Any? = null
    var titleClickAble = true
    var tag = "main"

    var initDate = String()
        set(value) {
            field = value
            initDay()
        }

    var searchYear = 0
    var searchMonth = 0

    fun monthApply(year: Int, month: Int) {
        searchYear = year
        searchMonth = month
        initCalendar()
    }

    private fun initDay(){
        if (initDate == "now") {
            searchYear = Utils.getYear()
            searchMonth = Utils.getMonth()
        } else {
            searchYear = initDate.split(".")[0].toInt()
            searchMonth = initDate.split(".")[1].toInt()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun removeDisposable(disposable: Disposable){
        compositeDisposable.remove(disposable)
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


    fun initCalendar() {
        observableDisposable?.let { removeDisposable(it) }

        observableDisposable = database.roomCalendarDataDao().getCalendar(tag,searchYear,searchMonth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                setCalendarItems(list)
            }) {
            }

        observableDisposable?.let { addDisposable(it) }
    }

    private fun setCalendarItems(list : List<RoomCalendarData>){

        title.value = if (language == "kr"){
            String.format("%d년 %d월", searchYear, searchMonth)
        }else{
            String.format("%s %d", Utils.getUkMonth(searchMonth), searchYear)
        }

        val lastDay = Utils.lastDay(searchYear, searchMonth)
        val startDay = Utils.getDateDay(
            String.format(
                "%d%s%s",
                searchYear,
                if (searchMonth < 10) "0$searchMonth" else searchMonth,
                "01"
            ),
            "yyyyMMdd"
        )

        val calendarItems = ArrayList<CalendarData>()

        if (startDay != 1) {
            for (i in 0 until startDay - 1) {
                calendarItems.add(CalendarData(id = i))
            }
        }

        for (i in 0 until lastDay) {
            val index = list.indexOfFirst { it.year == searchYear && it.month == searchMonth && it.day == i+1 && it.tag == tag }
            if(index == -1){
                calendarItems.add(
                    CalendarData(
                        if (calendarItems.isEmpty()) 0 else calendarItems[calendarItems.size - 1].id + 1,
                        true,
                        searchYear,
                        searchMonth,
                        (i + 1),
                        defaultImage,
                        imageVisible,
                        circleImage
                    )
                )
            }else{
                calendarItems.add(
                    CalendarData(
                        if (calendarItems.isEmpty()) 0 else calendarItems[calendarItems.size - 1].id + 1,
                        true,
                        searchYear,
                        searchMonth,
                        (i + 1),
                        list[index].imageUrl,
                        imageVisible,
                        circleImage
                    )
                )
            }
        }

        calendarData.value = calendarItems
        _changeMonth.value = Unit
    }
}