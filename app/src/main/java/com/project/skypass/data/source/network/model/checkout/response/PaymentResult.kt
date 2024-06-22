package com.project.skypass.data.source.network.model.checkout.response


import com.google.gson.annotations.SerializedName

data class PaymentResult(
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("payment_callback_data")
    var paymentCallbackData: String?,
    @SerializedName("payment_date")
    var paymentDate: String?,
    @SerializedName("payment_id")
    var paymentId: String?,
    @SerializedName("payment_method")
    var paymentMethod: String?,
    @SerializedName("payment_status")
    var paymentStatus: String?,
    @SerializedName("total_amount")
    var totalAmount: Int?,
    @SerializedName("transaction_id")
    var transactionId: String?,
    @SerializedName("transaction_token")
    var transactionToken: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: String?
)