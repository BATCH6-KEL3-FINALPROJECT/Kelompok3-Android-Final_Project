package com.project.skypass.data.source.network.model.register

data class RegisterItemResponse(
    val email: String?,
    val newUser: RegisterNewUserResponse?
)