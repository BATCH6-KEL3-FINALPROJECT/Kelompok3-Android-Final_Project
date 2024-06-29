package com.project.skypass.data.repository.ticket

import com.project.skypass.data.datasource.ticket.PrintTicketDataSource
import com.project.skypass.data.source.network.model.ticket.print.PrintTicketRequestResponse
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class PrintTicketRepositoryImpl(private val dataSource: PrintTicketDataSource) : PrintTicketRepository {
    override fun printTicket(
        token: String,
        id: String,
        email: String,
    ): Flow<ResultWrapper<Boolean>> {
        val bearerToken = "Bearer $token"
        return proceedFlow {
            dataSource.getTicket(
                token = bearerToken,
                id = id,
                email =
                    PrintTicketRequestResponse(
                        email = email,
                    ),
            ).isSuccess ?: false
        }
    }
}
