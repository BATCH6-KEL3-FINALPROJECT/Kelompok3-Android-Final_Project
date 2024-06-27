package com.project.skypass.data.source.network.service

import com.project.skypass.BuildConfig
import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.booking.GetBookingDataResponse
import com.project.skypass.data.source.network.model.checkout.request.CheckoutRequestResponse
import com.project.skypass.data.source.network.model.checkout.response.CheckoutResponse
import com.project.skypass.data.source.network.model.destinationfavorite.DestinationFavoriteResponse
import com.project.skypass.data.source.network.model.flight.detailflight.DetailFlightResponse
import com.project.skypass.data.source.network.model.flight.flightdata.GetAllFlightResponse
import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryResponse
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryResponse
import com.project.skypass.data.source.network.model.history.userhistory.UserHistoryResponse
import com.project.skypass.data.source.network.model.login.LoginItemResponse
import com.project.skypass.data.source.network.model.login.LoginRequestResponse
import com.project.skypass.data.source.network.model.notification.all.NotificationResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationResponse
import com.project.skypass.data.source.network.model.notification.update.UpdateNotificationResponse
import com.project.skypass.data.source.network.model.otp.ResendOtpRequestResponse
import com.project.skypass.data.source.network.model.otp.ResendOtpResponse
import com.project.skypass.data.source.network.model.otp.VerifyRequestResponse
import com.project.skypass.data.source.network.model.otp.VerifyResponse
import com.project.skypass.data.source.network.model.payment.PaymentResponse
import com.project.skypass.data.source.network.model.register.RegisterItemResponse
import com.project.skypass.data.source.network.model.register.RegisterRequestResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordRequestResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordResponse
import com.project.skypass.data.source.network.model.search.SearchResponse
import com.project.skypass.data.source.network.model.search.deletehistory.DeleteHistorySearchResponse
import com.project.skypass.data.source.network.model.search.gethistory.GetHistoryResponse
import com.project.skypass.data.source.network.model.search.posthistory.PostHistoryRespomse
import com.project.skypass.data.source.network.model.search.posthistory.request.HistoryRequestResponse
import com.project.skypass.data.source.network.model.seat.SeatResponse
import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.data.source.network.model.ticket.TicketResponse
import com.project.skypass.data.source.network.model.ticket.print.PrintTicketRequestResponse
import com.project.skypass.data.source.network.model.ticket.print.PrintTicketResponse
import com.project.skypass.data.source.network.model.user.detailuser.UserResponse
import com.project.skypass.data.source.network.model.user.edituser.EditUserResponse
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    //need expired token handler (DONE)
    @POST("auth/register")
    suspend fun doRegister(
        @Body registerRequest: RegisterRequestResponse
    ): Response<RegisterItemResponse>

    @POST("auth/verify")
    suspend fun doVerify(
        @Body verifyRequest: VerifyRequestResponse
    ): VerifyResponse

    //need expired token handler (DONE)
    @POST("auth/login")
    suspend fun doLogin(
        @Body loginRequest: LoginRequestResponse
    ): Response<LoginItemResponse>

    @POST("auth/resend-otp")
    suspend fun doResendOtp(
        @Body resendCodeRequest: ResendOtpRequestResponse
    ):ResendOtpResponse

    @POST("auth/reset-password")
    suspend fun doResetPassword(
        @Body resetPasswordRequest: ResetPasswordRequestResponse
    ): ResetPasswordResponse

    @GET("flight")
    suspend fun getFlightData(
        @Query("departure_city") departureCity: String? = null,
        @Query("arrival_city") arrivalCity: String? = null,
        @Query("seats_available") seatsAvailable: String? = null,
        @Query("flight_status") flightStatus: String? = null,
        @Query("seat_class") seatClass: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("departure_date") departureDate: String? = null,
    ): GetAllFlightResponse

    @GET("flight/{id}")
    suspend fun getDetailFlightData(
        @Path("id") id: String
    ): DetailFlightResponse

    @GET("user/{id}")
    suspend fun getUserData(
        @Path("id") id: String
    ): UserResponse

    //need expired token handler
    @Multipart
    @PATCH("user/{id}")
    suspend fun updateUserData(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone_number") phoneNumber: RequestBody,
        @Part image: MultipartBody.Part? = null
    ): EditUserResponse

    @DELETE("user/{id}")
    suspend fun deleteUser(
        @Path("id") id: String
    ): DeleteUserResponse

    @GET("airport/")
    suspend fun searchDestination(
        @Query("search") search: String? = null,
    ): SearchResponse

    @GET("seat/")
    suspend fun getSeatData(
        @Query("seat_class") seatClass: String? = null,
        @Query("flight_id") flightId: String? = null,
        @Query("limit") page: Int? = null
    ): SeatResponse

    @GET("ticket/{id}")
    suspend fun getTicketData(
        @Path("id") id: String
    ): TicketResponse

    //need expired token handler
    @GET("notification")
    suspend fun getAllNotification(
        @Header("Authorization") token: String,
    ): NotificationResponse

    //need expired token handler
    @GET("notification/{id}")
    suspend fun getDetailNotification(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DetailNotificationResponse

    @PATCH("notification/{id}")
    suspend fun updateNotification(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): UpdateNotificationResponse

    //need expired token handler
    @GET("booking/history")
    suspend fun getAllHistory(
        @Header("Authorization") token: String
    ): AllHistoryResponse

    //need expired token handler
    @GET("booking/history/{id}")
    suspend fun getBookingHistory(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part("search") search: String?,
        @Part("date") date: String?,
        @Part("until") until: String?
    ): UserHistoryResponse

    //need expired token handler
    @GET("booking/history/{id}")
    suspend fun getDetailHistory(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DetailHistoryResponse

    //need expired token handler
    @POST("transaction/booking")
    suspend fun bookingTicket(
        @Header("Authorization") token: String,
        @Body bookingRequest: CheckoutRequestResponse
    ): CheckoutResponse

    //need expired token handler
    @GET("transaction/booking/{id}")
    suspend fun getBooking(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): GetBookingDataResponse

    //need expired token handler
    @POST("transaction/payment/{id}")
    suspend fun createPayment(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): PaymentResponse

    //need expired token handler
    @DELETE("history/delete/{id}")
    suspend fun deleteHistorySearchHome(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteHistorySearchResponse

    //need expired token handler
    @GET("history/")
    suspend fun getAllHistorySearchHome(
        @Header("Authorization") token: String
    ): GetHistoryResponse

    //need expired token handler
    @POST("history/create")
    suspend fun createHistorySearchHome(
        @Header("Authorization") token: String,
        @Body historyRequest: HistoryRequestResponse
    ): PostHistoryRespomse

    @GET("flight/favorites")
    suspend fun getDestinationFavorite(): DestinationFavoriteResponse

    @POST("ticket/generate/{id}")
    suspend fun generateTicketToEmail(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body ticketRequest: PrintTicketRequestResponse
    ): PrintTicketResponse

    companion object {
        @JvmStatic
        operator fun invoke(): ApiService {
            val levelInterceptor = HttpLoggingInterceptor.Level.BODY
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(levelInterceptor)
            val okHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    //.addInterceptor(ErrorInterceptor())
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}