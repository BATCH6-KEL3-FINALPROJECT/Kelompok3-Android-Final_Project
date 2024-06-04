package com.project.skypass.data.repository.oauth

import android.content.Intent
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface OAuthRepository {
    /*fun signIn(): Flow<ResultWrapper<Boolean>>*/

    fun initiateSignIn(): Intent
    fun handleSignInResult(data: Intent?): Flow<ResultWrapper<Boolean>>
    fun signOut(): Flow<ResultWrapper<Boolean>>
}