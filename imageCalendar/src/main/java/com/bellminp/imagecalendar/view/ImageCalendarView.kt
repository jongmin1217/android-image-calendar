package com.bellminp.imagecalendar.view

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.pm.PermissionInfoCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bellminp.imagecalendar.R
import com.bellminp.imagecalendar.databinding.BmSelectCalendarBinding
import com.bellminp.imagecalendar.utils.Utils
import androidx.lifecycle.LifecycleOwner
import com.bellminp.imagecalendar.adapter.CalendarAdapter
import com.bellminp.imagecalendar.adapter.DayAdapter
import com.bellminp.imagecalendar.base.MvvmFrameLayout
import com.bellminp.imagecalendar.database.AppDatabase
import com.bellminp.imagecalendar.dialog.BottomSelectDialog
import com.bellminp.imagecalendar.listener.CalendarClickListener
import com.bellminp.imagecalendar.listener.CalendarListener
import com.bellminp.imagecalendar.listener.ChangeMonthListener
import com.bellminp.imagecalendar.model.CalendarData
import com.bellminp.imagecalendar.model.DayData
import com.bellminp.imagecalendar.utils.BindingAdapter
import com.bellminp.imagecalendar.utils.Constants

class ImageCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MvvmFrameLayout<ImageCalendarViewState, ImageCalendarViewModel>(context, attrs, defStyleAttr),
    CalendarListener {

    override val viewModel = ImageCalendarViewModel(AppDatabase.getInstance(context))
    private lateinit var binding: BmSelectCalendarBinding

    private lateinit var calendarClickListener : CalendarClickListener
    private lateinit var changeMonthListener: ChangeMonthListener

    private val calendarAdapter = CalendarAdapter(this)


    init {
        initBinding(context, attrs)
    }


    override fun onLifecycleOwnerAttached(lifecycleOwner: LifecycleOwner) {
        binding.lifecycleOwner = lifecycleOwner

        binding.vm = viewModel
        observeLiveData(lifecycleOwner)
    }

    private fun observeLiveData(lifecycleOwner: LifecycleOwner) {
        with(viewModel){
            changeMonth.observe(lifecycleOwner,{
                if(this@ImageCalendarView::changeMonthListener.isInitialized){
                    changeMonthListener.onChange(searchYear,searchMonth)
                }
            })
        }
    }

    private fun initBinding(context: Context, attrs: AttributeSet?) {

        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ImageCalendarView, 0, 0)

        try {
            with(viewModel){
                calendarType = attributes.getResourceId(
                    R.styleable.ImageCalendarView_bmCalendarType,
                    R.layout.bm_select_calendar
                )
                initDate = attributes.getString(R.styleable.ImageCalendarView_initDate)
                    ?: resources.getString(R.string.now)

                language =
                    attributes.getString(R.styleable.ImageCalendarView_language) ?: resources.getString(
                        R.string.kr
                    )

                imageVisible =
                    attributes.getBoolean(R.styleable.ImageCalendarView_imageVisible, true)

                tag =
                    attributes.getString(R.styleable.ImageCalendarView_calendarTag) ?: "main"

                circleImage =
                    attributes.getBoolean(R.styleable.ImageCalendarView_circleImage, false)

                titleClickAble =
                    attributes.getBoolean(R.styleable.ImageCalendarView_titleClickAble,true)

                if (attributes.hasValue(R.styleable.ImageCalendarView_defaultImageResource)) {
                    defaultImage =
                        attributes.getResourceId(R.styleable.ImageCalendarView_defaultImageResource, 0)
                }

                if (attributes.hasValue(R.styleable.ImageCalendarView_defaultImageUrl)) {
                    defaultImage =
                        attributes.getString(R.styleable.ImageCalendarView_defaultImageUrl)
                }
            }

        } finally {
            attributes.recycle()
        }

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, viewModel.calendarType, this, true)

        drawView()
        initListener()
    }

    private fun drawView() {
        with(viewModel){
            if (language == resources.getString(R.string.kr)) {
                val dayList = ArrayList<DayData>()
                for (i in 0..6) {
                    dayList.add(DayData(i.toLong(), resources.getStringArray(R.array.kr_day)[i]))
                    BindingAdapter.dayAdapter(binding.recyclerviewDay, DayAdapter(dayList))
                }
            } else {
                val dayList = ArrayList<DayData>()
                for (i in 0..6) {
                    dayList.add(DayData(i.toLong(), resources.getStringArray(R.array.uk_day)[i]))
                    BindingAdapter.dayAdapter(binding.recyclerviewDay, DayAdapter(dayList))
                }
            }

            BindingAdapter.calendarAdapter(binding.recyclerviewCalendar, calendarAdapter)

            initCalendar()
        }

    }

    fun setOnCalendarClickListener(listener : (CalendarData) -> Unit){
        calendarClickListener = object : CalendarClickListener{
            override fun onClick(calendarData: CalendarData) {
                listener(calendarData)
            }
        }
    }

    fun setOnChangeMonthListener(listener: (Int,Int) -> Unit){
        changeMonthListener = object : ChangeMonthListener{
            override fun onChange(year: Int, month: Int) {
                listener(year,month)
            }
        }
    }

    fun selectCalendar(year : Int, month : Int){
        if(Utils.ableDate(year, month)){
            with(viewModel){
                searchYear = year
                searchMonth = month
                viewModel.initCalendar()
            }
        }
    }


    private fun initListener() {
        when (getCalendarTypeName()) {
            resources.getString(R.string.select_calendar) -> {
                binding.tvMonth.setOnClickListener {
                    getFragmentManager(context)?.let { fragmentManager ->
                        BottomSelectDialog(viewModel).show(
                            fragmentManager,
                            Constants.SELECT_DATE
                        )
                    }
                }
            }
        }
    }

    private fun getFragmentManager(context: Context): FragmentManager? {
        return when (context) {
            is AppCompatActivity -> context.supportFragmentManager
            is ContextThemeWrapper -> getFragmentManager(context.baseContext)
            else -> null
        }
    }

    private fun getCalendarTypeName(): String {
        return if (viewModel.calendarType == R.layout.bm_select_calendar) resources.getString(R.string.select_calendar)
        else ""
    }

    override fun calendarClick(calendarData: CalendarData) {
        if(this::calendarClickListener.isInitialized){
            calendarClickListener.onClick(calendarData)
        }
    }


}