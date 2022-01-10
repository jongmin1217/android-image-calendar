package com.bellminp.imagecalendar.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.imagecalendar.adapter.DayAdapter

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
}