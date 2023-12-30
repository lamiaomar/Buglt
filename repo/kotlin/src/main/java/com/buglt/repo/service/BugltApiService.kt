package com.buglt.repo.service

import com.buglt.repo.dto.TicketDataDto
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

}


