package com.project.skypass.data.model

import java.util.UUID

data class User(
    var id: String = UUID.randomUUID().toString(),
    val userId: String?,
    val name: String?,
    val email: String?,
    val password: String?,
    val phoneNumber: String?,
    val role: String?,
    val token: String?,
    val photoUrl: String?,
    val imageId: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val isVerified: Boolean?,
)
