package com.project.skypass.data.source.network.model.register

data class RegisterResponse(
    val code: Int?,
    val data: RegisterItemResponse?,
    val is_success: Boolean?,
    val message: String?
)