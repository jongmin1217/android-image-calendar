package com.bellminp.samplelibrary

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bellminp.imagecalendar.database.ImageCalendar
import com.bellminp.imagecalendar.listener.DeleteCalendarCallback
import com.bellminp.imagecalendar.listener.InsertCalendarCallback
import com.bellminp.imagecalendar.model.DeleteCalendarData
import com.bellminp.imagecalendar.model.InsertCalendarData
import com.bellminp.samplelibrary.databinding.ActivitySampleCalendarBinding
import com.esafirm.imagepicker.features.ImagePicker

class SampleCalendarActivity : AppCompatActivity() {
    private val viewModel: SampleCalendarViewModel by viewModels()
    lateinit var binding: ActivitySampleCalendarBinding

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val image = ImagePicker.getImages(result.data)

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_calendar)
        binding.lifecycleOwner = this

        binding.vm = viewModel

        binding.btn.setOnClickListener {
//            if (binding.test1.text.isNotEmpty() && binding.test2.text.isNotEmpty() && binding.test3.text.isNotEmpty()) {
//                galleryLauncher.launch(
//                    ImagePicker.create(this@SampleCalendarActivity).single().showCamera(false)
//                        .folderMode(true)
//                        .getIntent(this@SampleCalendarActivity)
//                )
//            }

            val item = listOf(InsertCalendarData(
                2022, 1, 1,
                "main",
                R.drawable.img_notice_message
            ),InsertCalendarData(
                2022, 1, 2,
                "main",
                R.drawable.img_notice_message
            ),InsertCalendarData(
                2022, 1, 3,
                "main",
                R.drawable.img_notice_message
            ),InsertCalendarData(
                2022, 1, 4,
                "main",
                R.drawable.img_notice_message
            ))



            ImageCalendar(this).addCalendar(
                item,
                object : InsertCalendarCallback {
                    override fun onSuccess() {
                        Log.d("timber", "insert success")
                    }

                    override fun onFail(error: Throwable) {
                        Log.d("timber", "insert error $error")
                    }
                }
            )
        }



        binding.imageCalendarView.setOnChangeMonthListener { year, month ->

        }

        binding.imageCalendarView.setOnCalendarClickListener {
            if (it.image == null) {

                ImageCalendar(this).addCalendar(
                    InsertCalendarData(
                        it.year, it.month, it.day,
                        "main",
                        R.drawable.img_notice_message
                    ),
                    object : InsertCalendarCallback {
                        override fun onSuccess() {
                            Log.d("timber", "insert success")
                        }

                        override fun onFail(error: Throwable) {
                            Log.d("timber", "insert error $error")
                        }
                    }
                )
            } else {
                ImageCalendar(this).deleteCalendar(
                    DeleteCalendarData(
                        "main",
                        it.year, it.month, it.day
                    ),
                    object : DeleteCalendarCallback {
                        override fun onSuccess() {
                            Log.d("timber", "delete success")
                        }

                        override fun onFail(error: Throwable) {
                            Log.d("timber", "delete error $error")
                        }

                    }
                )
            }

        }

    }
}