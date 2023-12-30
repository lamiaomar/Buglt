package com.buglt.repo.datasource

import com.buglt.repo.body.CreateTicketBody
import com.buglt.repo.dto.TicketDataDto
import com.buglt.repo.service.BugltApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BaseRemoteDataSource @Inject constructor(
    private val bugltApiService: BugltApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun createTicket(createTicketBody: CreateTicketBody): TicketDataDto =
        withContext(ioDispatcher) {
            bugltApiService.createTicket(
                title = createTicketBody.title,
                description = createTicketBody.description,
                imageURL = createTicketBody.imageURL,
                platform = createTicketBody.platform
            )
        }
}