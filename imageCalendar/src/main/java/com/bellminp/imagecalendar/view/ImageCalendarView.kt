package com.bellminp.imagecalendar.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import com.bellminp.imagecalendar.R
import com.bellminp.imagecalendar.databinding.BmSelectCalendarBinding
import com.bellminp.imagecalendar.utils.Utils
import androidx.lifecycle.LifecycleOwner
import com.bellminp.imagecalendar.base.MvvmFrameLayout
import com.bellminp.imagecalendar.utils.LifecycleOwnerNotFoundException

class ImageCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MvvmFrameLayout<ImageCalendarViewState,ImageCalendarViewModel>(context, attrs, defStyleAttr){

    override val viewModel = ImageCalendarViewModel()
    lateinit var binding : BmSelectCalendarBinding

    private var calendarType = 0
    private var initDate = String()
    private var language = String()


    var title : String = ""
        set(value) {
            field = value
            viewModel.title.value = value
        }

    init {
        initBinding(context,attrs)
    }


    override fun onLifecycleOwnerAttached(lifecycleOwner: LifecycleOwner) {
        binding.lifecycleOwner = lifecycleOwner

        binding.vm = viewModel
        observeLiveData(lifecycleOwner)
    }

    private fun observeLiveData(lifecycleOwner: LifecycleOwner) {

    }

    private fun initBinding(context: Context, attrs: AttributeSet?) {

        val attributes = context.theme.obtainStyledAttributes(attrs,R.styleable.ImageCalendarView,0,0)

        try {
            calendarType = attributes.getResourceId(R.styleable.ImageCalendarView_bm_calendar_type, R.layout.bm_select_calendar)
            initDate = attributes.getString(R.styleable.ImageCalendarView_init_date)?:resources.getString(R.string.now)
            language = attributes.getString(R.styleable.ImageCalendarView_language)?:resources.getString(R.string.kr)
        }finally {
            attributes.recycle()
        }

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater,calendarType,this,true)

        drawView()
    }

    private fun drawView(){
        title = if(language == resources.getString(R.string.kr)){
            if(initDate == resources.getString(R.string.now)) String.format("%d년 %d월",Utils.getYear(),Utils.getMonth())
            else String.format("%s년 %d월",initDate.split(".")[0],initDate.split(".")[1].toInt())
        }else{
            if(initDate == resources.getString(R.string.now)) String.format("%s %d",Utils.getUkMonth(Utils.getMonth()),Utils.getYear())
            else String.format("%s %s",Utils.getUkMonth(initDate.split(".")[1].toInt()),initDate.split(".")[0])
        }
    }



    public fun getCalendarTypeName() : String{
        return if(calendarType == R.layout.bm_select_calendar) resources.getString(R.string.select_calendar)
        else ""
    }





}