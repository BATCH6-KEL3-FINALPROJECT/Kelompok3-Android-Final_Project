package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
    var filter_condition_1: String,
    var filter_condition_2: String
): Parcelable