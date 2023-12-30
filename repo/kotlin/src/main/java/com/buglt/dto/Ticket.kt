package com.buglt.dto

import com.squareup.moshi.Json
import java.io.Serializable

data class Ticket(
    val title: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "platform")
    val platformLabel: String
) : Serializable

data class TicketsDto(
    @Json(name = "items")
    var items: List<Ticket> = arrayListOf()
) : Serializable