package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notification (
    var title: String,
    var body: String,
    var category: String,
    var date: String,
    var status: Boolean
): Parcelable