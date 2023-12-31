package com.buglt.body
data class CreateTicketBody(
    var title: String,
    var description: String,
    var platform: String,
    var imageURL: String = ""
)