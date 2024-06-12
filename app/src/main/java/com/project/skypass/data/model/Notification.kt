package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Notification (
    var id: String? = UUID.randomUUID().toString(),
    var title: String,
    var body: String,
    var category: String,
    var date: String,
    var status: Boolean
): Parcelable