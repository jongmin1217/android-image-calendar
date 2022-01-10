package com.bellminp.imagecalendar.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.bellminp.imagecalendar.R
import com.bellminp.imagecalendar.databinding.BmBottomSelectBinding

import com.bellminp.imagecalendar.utils.Utils
import com.bellminp.imagecalendar.view.ImageCalendarViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSelectDialog(private val viewModel: ImageCalendarViewModel) : BottomSheetDialogFragment() {

    private lateinit var binding : BmBottomSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.bm_bottom_select,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        binding.dpYear.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        binding.dpMonth.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        binding.dpYear.maxValue = 2100
        binding.dpYear.minValue = 1900
        binding.dpYear.wrapSelectorWheel = false

        binding.dpMonth.maxValue = 12
        binding.dpMonth.minValue = 1
        binding.dpMonth.wrapSelectorWheel = true

        if(viewModel.language == resources.getString(R.string.kr)){
            binding.btnCancel.text = resources.getString(R.string.cancel_kr)
            binding.btnApply.text = resources.getString(R.string.apply_kr)
        }else{
            binding.btnCancel.text = resources.getString(R.string.cancel_uk)
            binding.btnApply.text = resources.getString(R.string.apply_uk)
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnApply.setOnClickListener {
            viewModel.monthApply(binding.dpYear.value,binding.dpMonth.value)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(),R.style.NewDialog).apply {
            setCanceledOnTouchOutside(true)
        }
    }
}