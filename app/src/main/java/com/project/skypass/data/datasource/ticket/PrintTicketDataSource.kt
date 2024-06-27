package com.project.skypass.data.datasource.ticket

import com.project.skypass.data.source.network.model.ticket.print.PrintTicketRequestResponse
import com.project.skypass.data.source.network.model.ticket.print.PrintTicketResponse

interface PrintTicketDataSource {
    suspend fun getTicket(token: String, id: String, email: PrintTicketRequestResponse): PrintTicketResponse
}