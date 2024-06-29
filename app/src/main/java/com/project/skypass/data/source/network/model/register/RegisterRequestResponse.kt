package com.project.skypass.data.source.network.model.register

data class RegisterRequestResponse(
    val email: String?,
    val name: String?,
    val password: String?,
    val phone_number: String?,
)
