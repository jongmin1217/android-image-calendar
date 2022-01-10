package com.bellminp.imagecalendar.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel


interface MvvmCustomView<V : MvvmCustomViewState, T : ViewModel> {
    val viewModel : T
    fun onLifecycleOwnerAttached(lifecycleOwner: LifecycleOwner)
}