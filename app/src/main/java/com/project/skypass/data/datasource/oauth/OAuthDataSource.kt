package com.project.skypass.data.datasource.oauth

interface OAuthDataSource {
    suspend fun signIn(): Boolean
    suspend fun signOut(): Boolean
}