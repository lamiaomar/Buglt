package com.buglt.dto

import com.squareup.moshi.Json
data class TicketDataDto(
    @Json(name="Ok")
    val Ok: String = "",
    @Json(name = "Fail")
    val fail: String = ""
)