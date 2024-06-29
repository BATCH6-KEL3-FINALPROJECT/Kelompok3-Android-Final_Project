package com.project.skypass.data.datasource.oauth

import android.content.Intent
import com.project.skypass.data.source.network.model.oauth.GoogleOAuthService

class OAuthDataSourceImpl(private val service: GoogleOAuthService) : OAuthDataSource {
    override fun initiateSignIn(): Intent {
        return service.getSignInIntent()
    }

    override suspend fun handleSignInResult(data: Intent?): Boolean {
        return service.handleSignInResult(data)
    }
    /*override suspend fun signIn(): Boolean {
        return service.signIn()
    }*/

    override suspend fun signOut(): Boolean {
        return service.signOut()
    }
}
