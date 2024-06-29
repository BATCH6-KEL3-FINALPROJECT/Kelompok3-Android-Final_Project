package com.project.skypass.data.source.network.model.login

data class LoginResponse(
    val code: Int?,
    val data: LoginItemResponse?,
    val is_success: Boolean?,
    val message: String?,
)
