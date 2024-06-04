package com.project.skypass.data.datasource.oauth

import android.content.Intent

interface OAuthDataSource {
    //suspend fun signIn(): Boolean
    fun initiateSignIn(): Intent
    suspend fun handleSignInResult(data: Intent?): Boolean
    suspend fun signOut(): Boolean
}