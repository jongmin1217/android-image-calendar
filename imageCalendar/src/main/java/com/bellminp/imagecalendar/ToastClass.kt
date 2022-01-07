package com.bellminp.imagecalendar

import android.content.Context
import android.widget.Toast

class ToastClass {
    companion object{
        fun testToast(context : Context, message : String) = Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}