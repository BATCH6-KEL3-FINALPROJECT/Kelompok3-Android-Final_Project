package com.project.skypass.di

import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.project.skypass.BuildConfig
import com.project.skypass.core.BaseViewModel
import com.project.skypass.data.datasource.auth.AuthDataStore
import com.project.skypass.data.datasource.auth.AuthDataStoreImpl
import com.project.skypass.data.datasource.flight.FlightDataSource
import com.project.skypass.data.datasource.flight.FlightDataSourceImpl
import com.project.skypass.data.datasource.history.HistoryDataSource
import com.project.skypass.data.datasource.history.HistoryDataSourceImpl
import com.project.skypass.data.datasource.home.favdestination.FavoriteDestinationDataSource
import com.project.skypass.data.datasource.home.favdestination.FavoriteDestinationDataSourceImpl
import com.project.skypass.data.datasource.home.orderHistory.OrderHistoryDataSource
import com.project.skypass.data.datasource.home.orderHistory.OrderHistoryDataSourceImpl
import com.project.skypass.data.datasource.home.seatclass.PriceClassDataSource
import com.project.skypass.data.datasource.home.seatclass.PriceClassDataSourceImpl
import com.project.skypass.data.datasource.oauth.OAuthDataSource
import com.project.skypass.data.datasource.oauth.OAuthDataSourceImpl
import com.project.skypass.data.datasource.preference.PrefDataSource
import com.project.skypass.data.datasource.preference.PrefDataSourceImpl
import com.project.skypass.data.datasource.profile.ProfileDataSource
import com.project.skypass.data.datasource.profile.ProfileDummyDataSource
import com.project.skypass.data.datasource.search.SearchDataSource
import com.project.skypass.data.datasource.search.SearchDataSourceImpl
import com.project.skypass.data.datasource.user.UserDataSource
import com.project.skypass.data.datasource.user.UserDataSourceImpl
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepositoryImpl
import com.project.skypass.data.repository.auth.AuthRepository
import com.project.skypass.data.repository.auth.AuthRepositoryImpl
import com.project.skypass.data.repository.flight.FlightRepository
import com.project.skypass.data.repository.flight.FlightRepositoryImpl
import com.project.skypass.data.repository.favoritedestination.FavoriteDestinationRepository
import com.project.skypass.data.repository.favoritedestination.FavoriteDestinationRepositoryImpl
import com.project.skypass.data.repository.history.HistoryRepository
import com.project.skypass.data.repository.history.HistoryRepositoryImpl
import com.project.skypass.data.repository.oauth.OAuthRepository
import com.project.skypass.data.repository.oauth.OAuthRepositoryImpl
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.pref.PrefRepositoryImpl
import com.project.skypass.data.repository.search.SearchRepository
import com.project.skypass.data.repository.search.SearchRepositoryImpl
import com.project.skypass.data.repository.seatclass.SeatClassRepository
import com.project.skypass.data.repository.seatclass.SeatClassRepositoryImpl
import com.project.skypass.data.repository.profile.ProfileRepository
import com.project.skypass.data.repository.profile.ProfileRepositoryImpl
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.data.repository.user.UserRepositoryImpl
import com.project.skypass.data.source.local.database.AppDatabase
import com.project.skypass.data.source.local.database.dao.OrderHistoryDao
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
import com.project.skypass.presentation.profile.changeprofile.ChangeProfileViewModel
import com.project.skypass.presentation.profile.profile.ProfileViewModel
import com.project.skypass.presentation.home.flightclass.FlightClassViewModel
import com.project.skypass.presentation.home.passengers.PassengersViewModel
import com.project.skypass.presentation.main.MainViewModel
import com.project.skypass.presentation.home.search.SearchViewModel
import com.project.skypass.presentation.profile.changeprofile.ChangeProfileViewModelExample
import com.project.skypass.presentation.profile.profile.ProfileViewModelExample
import com.project.skypass.presentation.profile.settingaccount.SettingsAccountViewModel
import com.project.skypass.presentation.flight.detail.FlightDetailViewModel
import com.project.skypass.presentation.flight.result.FlightResultViewModel
import com.project.skypass.presentation.history.HistoryViewModel
import com.project.skypass.utils.SharedPreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
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
        single<AppDatabase> { AppDatabase.getInstance(androidContext()) }
        single<OrderHistoryDao> { get<AppDatabase>().orderHistoryDao() }
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
            UserDataSourceImpl(get(),get())
        }
        single<ProfileDataSource> { ProfileDummyDataSource() }
        single<PriceClassDataSource> {
            PriceClassDataSourceImpl()
        }
        single<SearchDataSource> {
            SearchDataSourceImpl(get())
        }
        single<FavoriteDestinationDataSource>{
            FavoriteDestinationDataSourceImpl()
        }
        single<FlightDataSource> {
            FlightDataSourceImpl(get())
        }
        single<OrderHistoryDataSource> {
            OrderHistoryDataSourceImpl(get())
        }
        single<HistoryDataSource> {
            HistoryDataSourceImpl()
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
        single<FlightRepository> {
            FlightRepositoryImpl(get())
        }
        single<SeatClassRepository> {
            SeatClassRepositoryImpl(get())
        }
        single<SearchRepository> {
            SearchRepositoryImpl(get())
        }
        single<FavoriteDestinationRepository>{
            FavoriteDestinationRepositoryImpl(get())
        }
        single<ProfileRepository> {
            ProfileRepositoryImpl(get())
        }
        single<OrderHistoryRepository>{
            OrderHistoryRepositoryImpl(get())
        }
        single<ProfileRepository> { ProfileRepositoryImpl(get()) }
        single<HistoryRepository> { HistoryRepositoryImpl(get()) }
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
        /*viewModel { params ->
            FlightDetailViewModel(
                flightRepository = get(),
                extras = params.get(),
                orderHistoryRepository = get()
            )
        }*/
        viewModel{ FlightDetailViewModel(get(),get()) }
        viewModel { params ->
            FlightResultViewModel(
                get()
            )
        }
        viewModelOf(::PassengersViewModel)
        viewModelOf(::FlightClassViewModel)
        viewModelOf(::ProfileViewModel)
        viewModelOf(::ChangeProfileViewModel)
        viewModel {BaseViewModel(get())}
        viewModelOf(:: MainViewModel)
        viewModelOf(::HomeViewModel)
        viewModelOf(::SearchViewModel)
        viewModelOf(::ChangeProfileViewModelExample)
        viewModelOf(::ProfileViewModelExample)
        viewModelOf(::HistoryViewModel)
    }

    val module = listOf<Module>(
        networkModule,
        localModule,
        googleOAuthModule,
        dataSourceModule,
        viewModelModule,
        repositoryModule,
    )
}
