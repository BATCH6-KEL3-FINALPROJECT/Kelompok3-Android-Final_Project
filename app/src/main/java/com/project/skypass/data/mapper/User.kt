package com.project.skypass.data.mapper

import com.project.skypass.data.model.User
import com.project.skypass.data.source.network.model.user.detailuser.UserItemResponse

fun UserItemResponse?.toUser() =
    User(
        userId = this?.user_id,
        name = this?.name,
        email = this?.email,
        password = null,
        phoneNumber = this?.phone_number,
        role = this?.role,
        token = null,
        photoUrl = this?.image_url,
        imageId = this?.image_id,
        createdAt = this?.createdAt,
        updatedAt = this?.updatedAt,
        isVerified = this?.is_verified,
    )

fun Collection<UserItemResponse>?.toUserData() = this?.map { it.toUser() } ?: listOf()
