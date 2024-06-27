package com.project.skypass.data.datasource.ticket

import com.project.skypass.data.source.network.model.ticket.print.PrintTicketRequestResponse
import com.project.skypass.data.source.network.model.ticket.print.PrintTicketResponse
import com.project.skypass.data.source.network.service.ApiService

class PrintTicketDataSourceImpl(private val apiService: ApiService): PrintTicketDataSource {
    override suspend fun getTicket(
        token: String,
        id: String,
        email: PrintTicketRequestResponse
    ): PrintTicketResponse {
        return apiService.generateTicketToEmail(token, id, email)
    }
}