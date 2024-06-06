package com.project.skypass.data.source.network.model.register

data class RegisterNewUserResponse(
    val createdAt: String?,
    val email: String?,
    val is_verified: Boolean?,
    val name: String?,
    val password: String?,
    val phone_number: String?,
    val refresh_token: String?,
    val role: String?,
    val updatedAt: String?,
    val user_id: String?
)