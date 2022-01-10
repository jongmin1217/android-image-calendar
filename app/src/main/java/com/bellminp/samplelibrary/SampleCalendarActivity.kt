package com.bellminp.samplelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bellminp.imagecalendar.listener.CalendarClickListener
import com.bellminp.imagecalendar.model.CalendarData
import com.bellminp.imagecalendar.view.ImageCalendarView
import com.bellminp.samplelibrary.databinding.ActivitySampleCalendarBinding

class SampleCalendarActivity : AppCompatActivity() {
    private val viewModel : SampleCalendarViewModel by viewModels()
    lateinit var binding: ActivitySampleCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sample_calendar)
        binding.lifecycleOwner = this

        binding.vm = viewModel

        binding.btn.setOnClickListener {
            binding.imageCalendarView.selectCalendar(1994,12)
        }

        binding.imageCalendarView.setOnChangeMonthListener { year, month ->
            Log.d("timber","$year $month")
        }

        binding.imageCalendarView.setOnCalendarClickListener {
            Log.d("timber","$it")
        }

    }
}