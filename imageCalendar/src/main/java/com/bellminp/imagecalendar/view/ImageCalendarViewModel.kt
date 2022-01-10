package com.bellminp.imagecalendar.view

import android.content.res.Resources
import android.util.Log
import android.view.textclassifier.TextLanguage
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bellminp.imagecalendar.R
import com.bellminp.imagecalendar.model.CalendarData
import com.bellminp.imagecalendar.utils.SingleLiveEvent
import com.bellminp.imagecalendar.utils.Utils

class ImageCalendarViewModel : ViewModel() {
    private fun <T : Any?> SingleLiveEvent<T>.default(initialValue: T) =
        apply { setValue(initialValue) }

    val title = SingleLiveEvent<String>().default("")

    val calendarData = SingleLiveEvent<ArrayList<CalendarData>?>().default(null)

    var calendarType = 0
    var initDate = String()
        set(value) {
            field = value

            if (value == "now") {
                searchYear = Utils.getYear()
                searchMonth = Utils.getMonth()
            } else {
                searchYear = value.split(".")[0].toInt()
                searchMonth = value.split(".")[1].toInt()
            }
            Log.d("timber","$searchYear $searchMonth")
        }

    var language = String()
    var imageVisible = true
    var circleImage = false
    var defaultImage: Any? = null
    var tag = "main"

    var searchYear = 0
    var searchMonth = 0

    fun monthApply(year: Int, month: Int) {
        searchYear = year
        searchMonth = month
        initTitle()
        initCalendarItems()
    }

    fun initTitle(){
        title.value = if (language == "kr"){
            String.format("%d년 %d월", searchYear, searchMonth)
        }else{
            String.format("%s %d", Utils.getUkMonth(searchMonth), searchYear)
        }
    }


    fun initCalendarItems() {
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
                calendarItems.add(CalendarData(i, false, null, null, imageVisible, circleImage))
            }
        }

        for (i in 0 until lastDay) {
            calendarItems.add(
                CalendarData(
                    if (calendarItems.isEmpty()) 0 else calendarItems[calendarItems.size - 1].id + 1,
                    true,
                    (i + 1).toString(),
                    defaultImage,
                    imageVisible,
                    circleImage
                )
            )
        }

        calendarData.value = calendarItems
    }
}