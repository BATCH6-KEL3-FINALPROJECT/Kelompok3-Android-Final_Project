package com.project.skypass.di

import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.project.skypass.BuildConfig
import com.project.skypass.core.BaseViewModel
import com.project.skypass.data.datasource.auth.AuthDataStore
import com.project.skypass.data.datasource.auth.AuthDataStoreImpl
import com.project.skypass.data.datasource.checkout.CheckoutDataSource
import com.project.skypass.data.datasource.checkout.CheckoutDataSourceImpl
import com.project.skypass.data.datasource.flight.FlightDataSource
import com.project.skypass.data.datasource.flight.FlightDataSourceImpl
import com.project.skypass.data.datasource.history.HistoryDataSource
import com.project.skypass.data.datasource.history.HistoryDataSourceImpl
import com.project.skypass.data.datasource.home.banner.BannerDummyDataSource
import com.project.skypass.data.datasource.home.banner.BannerDummyDataSourceImpl
import com.project.skypass.data.datasource.home.favdestination.FavoriteDestinationDataSource
import com.project.skypass.data.datasource.home.favdestination.FavoriteDestinationDataSourceImpl
import com.project.skypass.data.datasource.home.orderHistory.OrderHistoryDataSource
import com.project.skypass.data.datasource.home.orderHistory.OrderHistoryDataSourceImpl
import com.project.skypass.data.datasource.home.seatclass.PriceClassDataSource
import com.project.skypass.data.datasource.home.seatclass.PriceClassDataSourceImpl
import com.project.skypass.data.datasource.notification.NotificationDataSource
import com.project.skypass.data.datasource.notification.NotificationDataSourceImpl
import com.project.skypass.data.datasource.oauth.OAuthDataSource
import com.project.skypass.data.datasource.oauth.OAuthDataSourceImpl
import com.project.skypass.data.datasource.preference.PrefDataSource
import com.project.skypass.data.datasource.preference.PrefDataSourceImpl
import com.project.skypass.data.datasource.profile.ProfileDataSource
import com.project.skypass.data.datasource.profile.ProfileDummyDataSource
import com.project.skypass.data.datasource.home.search.SearchDataSource
import com.project.skypass.data.datasource.home.search.SearchDataSourceImpl
import com.project.skypass.data.datasource.seat.SeatDataSource
import com.project.skypass.data.datasource.seat.SeatsDataSource
import com.project.skypass.data.datasource.seat.SeatsDataSourceImpl
import com.project.skypass.data.datasource.ticket.PrintTicketDataSource
import com.project.skypass.data.datasource.ticket.PrintTicketDataSourceImpl
import com.project.skypass.data.datasource.user.UserDataSource
import com.project.skypass.data.datasource.user.UserDataSourceImpl
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepositoryImpl
import com.project.skypass.data.repository.auth.AuthRepository
import com.project.skypass.data.repository.auth.AuthRepositoryImpl
import com.project.skypass.data.repository.bannerHome.BannerHomeRepository
import com.project.skypass.data.repository.bannerHome.BannerHomeRepositoryImpl
import com.project.skypass.data.repository.checkout.CheckoutRepository
import com.project.skypass.data.repository.checkout.CheckoutRepositoryImpl
import com.project.skypass.data.repository.flight.FlightRepository
import com.project.skypass.data.repository.flight.FlightRepositoryImpl
import com.project.skypass.data.repository.favoritedestination.FavoriteDestinationRepository
import com.project.skypass.data.repository.favoritedestination.FavoriteDestinationRepositoryImpl
import com.project.skypass.data.repository.history.HistoryRepository
import com.project.skypass.data.repository.history.HistoryRepositoryImpl
import com.project.skypass.data.repository.notification.NotificationRepository
import com.project.skypass.data.repository.notification.NotificationRepositoryImpl
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
import com.project.skypass.data.repository.seat.SeatRepository
import com.project.skypass.data.repository.seat.SeatRepositoryImpl
import com.project.skypass.data.repository.seat.SeatsRepository
import com.project.skypass.data.repository.seat.SeatsRepositoryImpl
import com.project.skypass.data.repository.ticket.PrintTicketRepository
import com.project.skypass.data.repository.ticket.PrintTicketRepositoryImpl
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
import com.project.skypass.presentation.checkout.checkoutDataOrder.CheckoutDataOrdersViewModel
import com.project.skypass.presentation.auth.resetpassword.ResetPasswordViewModel
import com.project.skypass.presentation.auth.verification.VerificationViewModel
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailViewModel
import com.project.skypass.presentation.checkout.checkoutPayment.CheckoutPaymentViewModel
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatViewModel
import com.project.skypass.presentation.checkout.checkoutmidtrans.CheckoutMidtransViewModel
import com.project.skypass.presentation.home.HomeViewModel
import com.project.skypass.presentation.profile.changeprofile.ChangeProfileViewModel
import com.project.skypass.presentation.profile.profile.ProfileViewModel
import com.project.skypass.presentation.home.flightclass.FlightClassViewModel
import com.project.skypass.presentation.home.passengers.PassengersViewModel
import com.project.skypass.presentation.main.MainViewModel
import com.project.skypass.presentation.home.search.SearchViewModel
import com.project.skypass.presentation.profile.changeprofile.ChangeProfileViewModelExample
import com.project.skypass.presentation.profile.settingaccount.SettingsAccountViewModel
import com.project.skypass.presentation.flight.detail.FlightDetailViewModel
import com.project.skypass.presentation.flight.filter.FilterViewModel
import com.project.skypass.presentation.flight.result.FlightResultViewModel
import com.project.skypass.presentation.history.HistoryViewModel
import com.project.skypass.presentation.history.detailhistory.DetailHistoryViewModel
import com.project.skypass.presentation.history.filter.search.SearchHistoryViewModel
import com.project.skypass.presentation.history.filter.date.CalendarHistoryViewModel
import com.project.skypass.presentation.home.calendar.CalendarHomeViewModel
import com.project.skypass.presentation.notification.NotificationViewModel
import com.project.skypass.presentation.notification.detailNotification.DetailNotificationViewModel
import com.project.skypass.presentation.profile.profile.ProfileViewModelExample
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
            FavoriteDestinationDataSourceImpl(get())
        }
        single<BannerDummyDataSource>{
            BannerDummyDataSourceImpl()
        }
        single<FlightDataSource> {
            FlightDataSourceImpl(get())
        }
        single<OrderHistoryDataSource> {
            OrderHistoryDataSourceImpl(get())
        }
        single<HistoryDataSource> {
            HistoryDataSourceImpl(get())
        }
        single<SeatDataSource> {
            SeatDataSource(get(), get(), get())
        }
        single<SeatsDataSource> {
            SeatsDataSourceImpl(get())
        }
        single<NotificationDataSource> {
            NotificationDataSourceImpl(get())
        }
        single<CheckoutDataSource> {
            CheckoutDataSourceImpl(get())
        }
        single<PrintTicketDataSource> {
            PrintTicketDataSourceImpl(get())
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
        single<BannerHomeRepository> {
            BannerHomeRepositoryImpl(get())
        }
        single<ProfileRepository> {
            ProfileRepositoryImpl(get())
        }
        single<OrderHistoryRepository>{
            OrderHistoryRepositoryImpl(get())
        }
        single<ProfileRepository> {
            ProfileRepositoryImpl(get())
        }
        single<HistoryRepository> {
            HistoryRepositoryImpl(get())
        }
        single<SeatRepository> {
            SeatRepositoryImpl(get())
        }
        single<SeatsRepository> {
            SeatsRepositoryImpl(get())
        }
        single<NotificationRepository> {
            NotificationRepositoryImpl(get())
        }
        single<CheckoutRepository> {
            CheckoutRepositoryImpl(get())
        }
        single<PrintTicketRepository> {
            PrintTicketRepositoryImpl(get())
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
        viewModelOf(::CheckoutDataOrdersViewModel)
        viewModelOf(::SearchViewModel)
        viewModelOf(::ChangeProfileViewModelExample)
        viewModelOf(::ProfileViewModelExample)
        viewModelOf(::HistoryViewModel)
        viewModelOf(::SearchHistoryViewModel)
        viewModelOf(::DetailHistoryViewModel)
        viewModelOf(::CalendarHomeViewModel)
        viewModelOf(::CalendarHomeViewModel)
        viewModelOf(::CheckoutSeatViewModel)
        viewModelOf(::NotificationViewModel)
        viewModelOf(::DetailNotificationViewModel)
        viewModelOf(::CheckoutDetailViewModel)
        viewModelOf(::CheckoutPaymentViewModel)
        viewModelOf(::CheckoutMidtransViewModel)
        viewModelOf(::FilterViewModel)
        viewModelOf(::CalendarHistoryViewModel)
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