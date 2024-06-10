package com.project.skypass.data.source.network.model.user.edituser

data class EditUserItemResponse(
    val createdAt: String?,
    val email: String?,
    val image_id: String?,
    val image_url: String?,
    val is_verified: Boolean?,
    val name: String?,
    val password: String?,
    val phone_number: String?,
    val refresh_token: Any?,
    val role: String?,
    val updatedAt: String?,
    val user_id: String?
)