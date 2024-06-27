package com.project.skypass.data.source.network.model.ticket.print


import com.google.gson.annotations.SerializedName

data class PrintTicketRequestResponse(
    @SerializedName("email")
    var email: String?
)