package com.bellminp.samplelibrary

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bellminp.imagecalendar.database.ImageCalendar
import com.bellminp.imagecalendar.listener.CalendarClickListener
import com.bellminp.imagecalendar.model.CalendarData
import com.bellminp.imagecalendar.model.RoomCalendarData
import com.bellminp.imagecalendar.view.ImageCalendarView
import com.bellminp.samplelibrary.databinding.ActivitySampleCalendarBinding
import com.esafirm.imagepicker.features.ImagePicker

class SampleCalendarActivity : AppCompatActivity() {
    private val viewModel : SampleCalendarViewModel by viewModels()
    lateinit var binding: ActivitySampleCalendarBinding

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val image = ImagePicker.getImages(result.data)
            ImageCalendar(this).addCalendar(
                binding.test1.text.toString().toInt(),binding.test2.text.toString().toInt(),binding.test3.text.toString().toInt(),
                "main",
                image[0].uri
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sample_calendar)
        binding.lifecycleOwner = this

        binding.vm = viewModel

        binding.btn.setOnClickListener {
            if(binding.test1.text.isNotEmpty() && binding.test2.text.isNotEmpty() && binding.test3.text.isNotEmpty()){
                galleryLauncher.launch(
                    ImagePicker.create(this@SampleCalendarActivity).single().showCamera(false).folderMode(true)
                        .getIntent(this@SampleCalendarActivity)
                )
            }
        }

        binding.imageCalendarView.setOnChangeMonthListener{ year,month->

        }

        binding.imageCalendarView.setOnCalendarClickListener {
            if(it.image == null){
                ImageCalendar(this).addCalendar(
                    it.year,it.month,it.day,
                    "main",
                    R.drawable.img_notice_message
                )
            }else{
                ImageCalendar(this).deleteCalendar("main",it.year,it.month,it.day)
            }

        }

    }
}