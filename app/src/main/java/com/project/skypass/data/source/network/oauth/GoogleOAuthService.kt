package com.project.skypass.data.source.network.oauth

interface GoogleOAuthService {
    suspend fun signIn(): Boolean
    suspend fun signOut(): Boolean
}