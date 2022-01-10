package com.bellminp.imagecalendar.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MvvmCustomViewStateWrapper(
    val superState: Parcelable?
): Parcelable