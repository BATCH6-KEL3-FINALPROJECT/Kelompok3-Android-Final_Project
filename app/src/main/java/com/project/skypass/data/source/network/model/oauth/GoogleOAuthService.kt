package com.project.skypass.data.source.network.model.oauth

import android.content.Intent

interface GoogleOAuthService {
    //suspend fun signIn(): Boolean
    fun getSignInIntent(): Intent
    fun handleSignInResult(data: Intent?): Boolean
    suspend fun signOut(): Boolean
}