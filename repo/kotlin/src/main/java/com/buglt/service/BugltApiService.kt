package com.buglt.service

import com.buglt.dto.TicketDataDto
import com.buglt.dto.TicketsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BugltApiService {
    @GET(ENDPOINT)
    suspend fun createTicket(
        @Query("title") title: String,
        @Query("description") description: String,
        @Query("platform") platform: String,
        @Query("image") imageURL: String,
        @Query("action") action: String = CREATE_ACTION
    ): TicketDataDto

    @GET(ENDPOINT)
    suspend fun getTickets(
        @Query("action") action: String = CREATE_GET_TICKET
    ) : TicketsDto
}


