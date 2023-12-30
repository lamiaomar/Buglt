package com.buglt.repo.domin.repository

import com.buglt.repo.body.CreateTicketBody
import com.buglt.repo.datasource.BaseRemoteDataSource
import com.buglt.repo.dto.TicketDataDto
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