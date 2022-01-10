package com.bellminp.imagecalendar.utils

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
    }
}