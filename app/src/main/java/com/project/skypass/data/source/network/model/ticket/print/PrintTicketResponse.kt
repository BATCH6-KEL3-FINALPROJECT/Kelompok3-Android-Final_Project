package com.project.skypass.data.source.network.model.ticket.print

import com.google.gson.annotations.SerializedName

data class PrintTicketResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: Data?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?,
)
