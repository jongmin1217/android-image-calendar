package com.bellminp.samplelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
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

    }
}