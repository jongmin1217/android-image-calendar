package com.bellminp.imagecalendar.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bellminp.imagecalendar.databinding.ItemCalendarBinding
import com.bellminp.imagecalendar.listener.CalendarListener
import com.bellminp.imagecalendar.model.CalendarData

@SuppressLint("NotifyDataSetChanged")
class CalendarAdapter(private val listener : CalendarListener) : RecyclerView.Adapter<CalendarAdapter.CalendarHolder>(){

    var items = ArrayList<CalendarData>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder {
        val binding = ItemCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarHolder(binding,listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
        holder.bind(items[position])
    }

    class CalendarHolder(private val binding: ItemCalendarBinding,private val listener: CalendarListener):
        RecyclerView.ViewHolder(binding.root){

        fun bind(calendarData: CalendarData){
            binding.model = calendarData

            binding.layout.setOnClickListener {
                listener.calendarClick(calendarData)
            }
        }
    }
}