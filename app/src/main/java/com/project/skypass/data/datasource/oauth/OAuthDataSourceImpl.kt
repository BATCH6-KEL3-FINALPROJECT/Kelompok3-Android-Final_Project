package com.project.skypass.data.datasource.oauth

import com.project.skypass.data.source.network.oauth.GoogleOAuthService

class OAuthDataSourceImpl(private val service: GoogleOAuthService): OAuthDataSource {
    override suspend fun signIn(): Boolean {
        return service.signIn()
    }

    override suspend fun signOut(): Boolean {
        return service.signOut()
    }
}