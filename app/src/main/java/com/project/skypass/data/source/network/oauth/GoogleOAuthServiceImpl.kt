package com.project.skypass.data.source.network.oauth

import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.project.skypass.data.mapper.toUserOAuth

class GoogleOAuthServiceImpl(
    private val googleSignInClient: GoogleSignInClient,
): GoogleOAuthService {
    override fun getSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    override fun handleSignInResult(data: Intent?): Boolean {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        return try {
            val account = task.getResult(ApiException::class.java)
            account?.toUserOAuth()
            account?.idToken != null
        } catch (e: ApiException) {
            e.printStackTrace()
            Log.e("GoogleOAuthService", "handleSignInResult: ${e.message.toString()}")
            false
        }
    }

    /*override suspend fun signIn(): Boolean {
        val signInIntent = googleSignInClient.signInIntent
        val task = GoogleSignIn.getSignedInAccountFromIntent(signInIntent)
        return try {
            val account = task.getResult(ApiException::class.java)
            account?.toUserOAuth()
            return account.idToken != null
        } catch (e: ApiException) {
            e.printStackTrace()
            false
        }
    }*/

    override suspend fun signOut(): Boolean {
        googleSignInClient.signOut()
        return true
    }

}