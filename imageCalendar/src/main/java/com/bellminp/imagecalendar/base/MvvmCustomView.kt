package com.bellminp.imagecalendar.base

import androidx.lifecycle.LifecycleOwner


interface MvvmCustomView<V : MvvmCustomViewState, T : MvvmCustomViewModel<V>> {
    val viewModel : T
    fun onLifecycleOwnerAttached(lifecycleOwner: LifecycleOwner)
}