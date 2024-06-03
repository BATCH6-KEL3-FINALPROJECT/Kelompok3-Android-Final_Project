package com.project.skypass.data.repository.oauth

import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface OAuthRepository {
    fun signIn(): Flow<ResultWrapper<Boolean>>
    fun signOut(): Flow<ResultWrapper<Boolean>>
}