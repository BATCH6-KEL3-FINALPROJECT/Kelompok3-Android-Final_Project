package com.project.skypass.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.auth.AuthRepository
import com.project.skypass.data.repository.oauth.OAuthRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.source.network.model.login.LoginResponse
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val prefRepository: PrefRepository,
    private val oAuthRepository: OAuthRepository
): ViewModel() {
    fun doLogin(
        email: String,
        password: String
    ): LiveData<ResultWrapper<LoginResponse>> {
        return authRepository.doLogin(email, password).asLiveData(Dispatchers.IO)
    }

    fun setToken(token: String) {
        return prefRepository.setToken(token)
    }

    fun setLogin(isLogin: Boolean) {
        return prefRepository.setLogin(isLogin)
    }

    fun doLoginOAuth(): LiveData<ResultWrapper<Boolean>> {
        return oAuthRepository.signIn().asLiveData(Dispatchers.IO)
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }
}