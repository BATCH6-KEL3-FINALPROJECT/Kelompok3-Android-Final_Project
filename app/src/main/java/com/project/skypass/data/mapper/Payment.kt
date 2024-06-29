package com.project.skypass.data.mapper

import com.project.skypass.data.model.Payment
import com.project.skypass.data.source.network.model.payment.PaymentItemResponse

fun PaymentItemResponse?.toPayment() =
    Payment(
        tokenMidtrans = this?.token.orEmpty(),
        urlMidtrans = this?.url.orEmpty(),
    )
