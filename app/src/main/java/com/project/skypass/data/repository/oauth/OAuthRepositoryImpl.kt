package com.project.skypass.data.repository.oauth

import android.content.Intent
import com.project.skypass.data.datasource.oauth.OAuthDataSource
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class OAuthRepositoryImpl(private val oAuthRepository: OAuthDataSource): OAuthRepository {
    override fun initiateSignIn(): Intent {
        return oAuthRepository.initiateSignIn()
    }

    override fun handleSignInResult(data: Intent?): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            oAuthRepository.handleSignInResult(data)
        }
    }

    /*override fun signIn(): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            oAuthRepository.signIn()
        }
    }*/

    override fun signOut(): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            oAuthRepository.signOut()
        }
    }
}