package com.bellminp.imagecalendar.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bellminp.imagecalendar.R
import com.bellminp.imagecalendar.base.BaseView
import com.bellminp.imagecalendar.databinding.BmSelectCalendarBinding
import com.bellminp.imagecalendar.utils.Utils
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val viewModel = ImageCalendarViewModel()
    lateinit var binding : BmSelectCalendarBinding

    private var calendarType = 0
    private var firstDate = String()

    private lateinit var tvTitle : TextView

    var title : String = ""
        set(value) {
            field = value
            tvTitle.text = value
        }

    init {
        initBinding(context,attrs)
    }

    fun initBinding(context: Context, attrs: AttributeSet?) {

        val attributes = context.theme.obtainStyledAttributes(attrs,R.styleable.ImageCalendarView,0,0)

        try {
            calendarType = attributes.getResourceId(R.styleable.ImageCalendarView_bm_calendar_type, R.layout.bm_select_calendar)
            firstDate = attributes.getString(R.styleable.ImageCalendarView_init_date)?:resources.getString(R.string.now)
        }finally {
            attributes.recycle()
        }

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater,calendarType,this,true)
        binding.vm = viewModel
        drawView()
    }

    private fun drawView(){
        tvTitle = findViewById(R.id.tv_month)

        title = if(firstDate == resources.getString(R.string.now)) String.format("%d년 %d월",Utils.getYear(),Utils.getMonth())
        else String.format("%s년 %d월",firstDate.split(".")[0],firstDate.split(".")[1].toInt())
    }



    public fun getCalendarTypeName() : String{
        return if(calendarType == R.layout.bm_select_calendar) resources.getString(R.string.select_calendar)
        else ""
    }



}