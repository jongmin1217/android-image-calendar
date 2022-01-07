package com.bellminp.imagecalendar.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.databinding.ViewDataBinding

abstract class BaseView <B : ViewDataBinding, VM : BaseViewModel>@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    abstract val viewModel: VM
    lateinit var binding: B
    abstract fun initBinding()
}