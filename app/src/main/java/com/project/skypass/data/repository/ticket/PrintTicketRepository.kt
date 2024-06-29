package com.project.skypass.data.repository.ticket

import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface PrintTicketRepository {
    fun printTicket(
        token: String,
        id: String,
        email: String,
    ): Flow<ResultWrapper<Boolean>>
}
