package com.project.skypass.data.mapper

import com.project.skypass.data.model.User
import com.project.skypass.data.source.network.model.user.edituser.EditUserItemResponse

fun EditUserItemResponse?.toEditUser() =
    User(
        userId = this?.user_id.orEmpty(),
        name = this?.name.orEmpty(),
        email = this?.email.orEmpty(),
        password = this?.password.orEmpty(),
        phoneNumber = this?.phone_number.orEmpty(),
        role = this?.role.orEmpty(),
        token = null,
        photoUrl = this?.image_url.orEmpty(),
        imageId = this?.image_id.orEmpty(),
        createdAt = this?.createdAt.orEmpty(),
        updatedAt = this?.updatedAt.orEmpty(),
        isVerified = this?.is_verified ?: false,
    )

fun Collection<EditUserItemResponse>?.toEditUsers() = this?.map { it.toEditUser() } ?: listOf()
