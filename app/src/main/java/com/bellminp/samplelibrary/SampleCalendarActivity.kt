package com.bellminp.samplelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.bellminp.imagecalendar.view.ImageCalendarView

class SampleCalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_calendar)

        findViewById<Button>(R.id.tv_test).setOnClickListener {
            Log.d("timber","???????????????????????")
            val test = findViewById<ImageCalendarView>(R.id.image_calendar_view).title
            findViewById<ImageCalendarView>(R.id.image_calendar_view).title = test+"z"
        }
    }
}