package com.buglt.repo.body
data class CreateTicketBody(
    var title: String,
    var description: String,
    var platform: String,
    var imageURL: String = ""
)