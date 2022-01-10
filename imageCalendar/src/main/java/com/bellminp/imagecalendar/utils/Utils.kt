package com.bellminp.imagecalendar.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{
        fun getYear() : Int = Calendar.getInstance().get(Calendar.YEAR)
        fun getMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1
        fun getDay(): Int = Calendar.getInstance().get(Calendar.DATE)

        fun getUkMonth(month : Int) : String{
            return when(month){
                1 -> "January"
                2 -> "February"
                3 -> "March"
                4 -> "April"
                5 -> "May"
                6 -> "June"
                7 -> "July"
                8 -> "August"
                9 -> "September"
                10 -> "October"
                11 -> "November"
                12 -> "December"
                else -> "Other"
            }
        }

        fun lastDay(year : Int, month : Int) : Int{
            val cal = Calendar.getInstance()

            cal.set(year,month-1,1)

            return cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        }

        @SuppressLint("SimpleDateFormat")
        fun getDateDay(date: String, dateType: String): Int {

            val dateFormat = SimpleDateFormat(dateType)
            val nDate = dateFormat.parse(date)

            nDate?.let {
                val cal = Calendar.getInstance()
                cal.time = it

                return cal.get(Calendar.DAY_OF_WEEK)
            }

            return 0
        }

        fun ableDate(year: Int,month: Int) : Boolean{
            return year in 1900..2100 && month in 1..12
        }
    }
}