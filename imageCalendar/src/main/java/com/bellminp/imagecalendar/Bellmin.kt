package com.bellminp.imagecalendar

import android.content.Context
import android.widget.Toast

class Bellmin {
    companion object{
        fun action(context: Context, message : String) = Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}