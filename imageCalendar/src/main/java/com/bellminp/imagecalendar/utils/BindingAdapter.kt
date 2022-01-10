package com.bellminp.imagecalendar.utils

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.imagecalendar.adapter.CalendarAdapter
import com.bellminp.imagecalendar.adapter.DayAdapter
import com.bellminp.imagecalendar.model.CalendarData
import com.bumptech.glide.Glide

object BindingAdapter {

    fun dayAdapter(view : RecyclerView, adapter : DayAdapter){
        if(!adapter.hasObservers()) adapter.setHasStableIds(true)

        view.layoutManager = GridLayoutManager(view.context,7)
        view.adapter = adapter

        val animator = view.itemAnimator
        if(animator is SimpleItemAnimator){
            animator.supportsChangeAnimations = false
        }
    }

    fun calendarAdapter(view : RecyclerView, adapter : CalendarAdapter){
        if(!adapter.hasObservers()) adapter.setHasStableIds(true)

        view.layoutManager = GridLayoutManager(view.context,7)
        view.adapter = adapter

        val animator = view.itemAnimator
        if(animator is SimpleItemAnimator){
            animator.supportsChangeAnimations = false
        }
    }

    @BindingAdapter("setCalendarData")
    @JvmStatic
    fun setCalendarData(view : RecyclerView, items : ArrayList<CalendarData>?){
        view.adapter?.let {
            items?.let { items ->
                if(view.adapter is CalendarAdapter){
                    val adapter = (view.adapter as CalendarAdapter)
                    adapter.items = items
                }
            }
        }
    }

    @BindingAdapter("setImage")
    @JvmStatic
    fun setImage(imageView: ImageView, url: Any?) {
        url?.let {
            Glide.with(imageView)
                .load(it)
                .into(imageView)
        }
    }

}