package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class BannerHome(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val city: String,
    val imageUrl: String,
    val description: String,
    val longDescription: String,
    val linkInfo: String,
) : Parcelable
