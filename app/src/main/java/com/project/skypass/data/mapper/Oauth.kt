package com.project.skypass.data.mapper

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.project.skypass.data.model.User

fun GoogleSignInAccount?.toUserOAuth() =
    this?.let {
        User(
            userId = this.id,
            name = this.displayName,
            email = this.email,
            photoUrl = this.photoUrl.toString(),
            token = this.idToken,
            phoneNumber = null,
            role = null,
            password = null,
            createdAt = null,
            updatedAt = null,
            imageId = null,
            isVerified = false,
        )
    }
