package com.bellminp.imagecalendar.utils

import java.util.*

class Utils {
    companion object{
        fun getYear() : Int = Calendar.getInstance().get(Calendar.YEAR)
        fun getMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1
        fun getDay(): Int = Calendar.getInstance().get(Calendar.DATE)
    }
}