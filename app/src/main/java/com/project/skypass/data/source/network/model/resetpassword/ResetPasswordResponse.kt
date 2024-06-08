package com.project.skypass.data.source.network.model.resetpassword

data class ResetPasswordResponse(
    val code: Int?,
    val is_success: Boolean?,
    val message: String?,
    val token: String?
)