package com.buglt.domin.repository

import com.buglt.body.CreateTicketBody
import com.buglt.datasource.BaseRemoteDataSource
import com.buglt.dto.TicketDataDto
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val baseRemoteDataSource: BaseRemoteDataSource
) {
    suspend fun createTicket(createTicketBody: CreateTicketBody): TicketDataDto {
        return baseRemoteDataSource.createTicket(
            createTicketBody = createTicketBody
        )
    }
}