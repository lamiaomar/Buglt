package com.buglt.domin.repository

import com.buglt.body.CreateTicketBody
import com.buglt.datasource.BaseRemoteDataSource
import com.buglt.dto.TicketDataDto
import com.buglt.dto.TicketsDto
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val baseRemoteDataSource: BaseRemoteDataSource
) {
    suspend fun createTicket(createTicketBody: CreateTicketBody): TicketDataDto {
        return baseRemoteDataSource.createTicket(
            createTicketBody = createTicketBody
        )
    }

    suspend fun getTickets(): TicketsDto {
        return baseRemoteDataSource.getTickets()
    }
}