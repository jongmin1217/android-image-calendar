package com.bellminp.imagecalendar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bellminp.imagecalendar.databinding.ItemDayBinding
import com.bellminp.imagecalendar.model.DayData

@SuppressLint("NotifyDataSetChanged")
class DayAdapter(private val items : ArrayList<DayData>) : RecyclerView.Adapter<DayAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return items[position].id
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ItemDayBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(dayData: DayData){
            binding.model = dayData
        }
    }
}