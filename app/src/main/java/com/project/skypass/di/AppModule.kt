package com.project.skypass.di

import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.project.skypass.BuildConfig
import com.project.skypass.data.datasource.auth.AuthDataStore
import com.project.skypass.data.datasource.auth.AuthDataStoreImpl
import com.project.skypass.data.datasource.home.PriceClassDataSource
import com.project.skypass.data.datasource.home.PriceClassDataSourceImpl
import com.project.skypass.data.datasource.oauth.OAuthDataSource
import com.project.skypass.data.datasource.oauth.OAuthDataSourceImpl
import com.project.skypass.data.datasource.preference.PrefDataSource
import com.project.skypass.data.datasource.preference.PrefDataSourceImpl
import com.project.skypass.data.datasource.user.UserDataSource
import com.project.skypass.data.datasource.user.UserDataSourceImpl
import com.project.skypass.data.repository.auth.AuthRepository
import com.project.skypass.data.repository.auth.AuthRepositoryImpl
import com.project.skypass.data.repository.oauth.OAuthRepository
import com.project.skypass.data.repository.oauth.OAuthRepositoryImpl
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.pref.PrefRepositoryImpl
import com.project.skypass.data.repository.seatclass.SeatClassRepository
import com.project.skypass.data.repository.seatclass.SeatClassRepositoryImpl
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.data.repository.user.UserRepositoryImpl
import com.project.skypass.data.source.local.pref.UserPreference
import com.project.skypass.data.source.local.pref.UserPreferenceImpl
import com.project.skypass.data.source.network.model.oauth.GoogleOAuthService
import com.project.skypass.data.source.network.model.oauth.GoogleOAuthServiceImpl
import com.project.skypass.data.source.network.service.ApiService
import com.project.skypass.presentation.auth.forgetpassword.ForgotPasswordViewModel
import com.project.skypass.presentation.auth.login.LoginViewModel
import com.project.skypass.presentation.onboarding.OnboardingViewModel
import com.project.skypass.presentation.splashscreen.SplashViewModel
import com.project.skypass.presentation.auth.register.RegisterViewModel
import com.project.skypass.presentation.auth.resetpassword.ResetPasswordViewModel
import com.project.skypass.presentation.auth.verification.VerificationViewModel
import com.project.skypass.presentation.home.HomeViewModel
import com.project.skypass.presentation.home.flightclass.FlightClassViewModel
import com.project.skypass.presentation.home.passengers.PassengersViewModel
import com.project.skypass.presentation.home.search.SearchViewModel
import com.project.skypass.presentation.profile.SettingsAccountViewModel
import com.project.skypass.utils.SharedPreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {

    private val networkModule: Module = module {
        single<ApiService> {
            ApiService.invoke()
        }
    }

    private val localModule = module {
        single<SharedPreferences> {
            SharedPreferenceUtils.createPreference(androidContext(), UserPreferenceImpl.PREF_NANE)
        }
        single<UserPreference> {
            UserPreferenceImpl(get())
        }
    }

    private val googleOAuthModule = module {
        single {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(BuildConfig.CLIENT_ID_OAUTH2)
                .build()
            GoogleSignIn.getClient(androidContext(), gso)
        }
        single<GoogleOAuthService> {
            GoogleOAuthServiceImpl(get())
        }
        single<OAuthDataSource> {
            OAuthDataSourceImpl(get())
        }
        single<OAuthRepository> {
            OAuthRepositoryImpl(get())
        }
    }

    private val dataSourceModule = module {
        single<PrefDataSource> {
            PrefDataSourceImpl(get())
        }
        single<AuthDataStore> {
            AuthDataStoreImpl(get())
        }
        single<UserDataSource> {
            UserDataSourceImpl(get())
        }
        single<PriceClassDataSource> {
            PriceClassDataSourceImpl()
        }
    }

    private val repositoryModule = module {
        single<PrefRepository>{
            PrefRepositoryImpl(get())
        }
        single<AuthRepository> {
            AuthRepositoryImpl(get())
        }
        single<UserRepository> {
            UserRepositoryImpl(get())
        }
        single<SeatClassRepository> {
            SeatClassRepositoryImpl(get())
        }
    }

    private val viewModelModule = module {
        viewModelOf(::SplashViewModel)
        viewModelOf(::OnboardingViewModel)
        viewModelOf(::LoginViewModel)
        viewModelOf(::RegisterViewModel)
        viewModelOf(::VerificationViewModel)
        viewModelOf(::SettingsAccountViewModel)
        viewModelOf(::ResetPasswordViewModel)
        viewModelOf(::ForgotPasswordViewModel)
        viewModelOf(::PassengersViewModel)
        viewModelOf(::FlightClassViewModel)
        viewModelOf(::HomeViewModel)
        viewModelOf(::SearchViewModel)
    }

    val module = listOf<Module>(
        networkModule,
        localModule,
        googleOAuthModule,
        dataSourceModule,
        viewModelModule,
        repositoryModule
    )
}