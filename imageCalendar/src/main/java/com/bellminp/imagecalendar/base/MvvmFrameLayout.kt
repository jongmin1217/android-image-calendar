package com.bellminp.imagecalendar.base

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.bellminp.imagecalendar.utils.LifecycleOwnerNotFoundException

abstract class MvvmFrameLayout<V : MvvmCustomViewState, T : ViewModel>(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    internal abstract val viewModel : T
    internal abstract fun onLifecycleOwnerAttached(lifecycleOwner: LifecycleOwner)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val lifecycleOwner = context as? LifecycleOwner ?: throw LifecycleOwnerNotFoundException()
        onLifecycleOwnerAttached(lifecycleOwner)
    }

    override fun onSaveInstanceState() = MvvmCustomViewStateWrapper(super.onSaveInstanceState())

    @Suppress("UNCHECKED_CAST")
    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is MvvmCustomViewStateWrapper) {
            super.onRestoreInstanceState(state.superState)
        }
    }
}