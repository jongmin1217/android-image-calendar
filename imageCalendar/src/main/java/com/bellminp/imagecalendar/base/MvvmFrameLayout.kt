package com.bellminp.imagecalendar.base

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import com.bellminp.imagecalendar.utils.LifecycleOwnerNotFoundException

abstract class MvvmFrameLayout<V : MvvmCustomViewState, T : MvvmCustomViewModel<V>>(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), MvvmCustomView<V, T> {
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