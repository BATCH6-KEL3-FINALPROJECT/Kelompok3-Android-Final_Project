package com.project.skypass.data.repository.oauth

import com.project.skypass.data.datasource.oauth.OAuthDataSource
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class OAuthRepositoryImpl(private val oAuthRepository: OAuthDataSource): OAuthRepository {
    override fun signIn(): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            oAuthRepository.signIn()
        }
    }

    override fun signOut(): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            oAuthRepository.signOut()
        }
    }
}