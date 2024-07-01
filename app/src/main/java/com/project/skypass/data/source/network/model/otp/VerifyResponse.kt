package com.project.skypass.data.source.network.model.otp

data class VerifyResponse(
    val code: Int?,
    val data: List<Data>?,
    val is_success: Boolean?,
    val message: String?,
)
