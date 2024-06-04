package com.project.skypass.data.mapper

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.project.skypass.data.model.User

fun GoogleSignInAccount?.toUserOAuth() =
    this?.let {
        User(
            id = this.id,
            name = this.displayName,
            email = this.email,
            photoUrl = this.photoUrl.toString(),
            token = this.idToken,
            phoneNumber = null,
            roles = null
        )
    }