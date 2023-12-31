package com.buglt.dto

import com.squareup.moshi.Json
import java.io.Serializable

data class ImagesDto(
    @Json(name = "images")
    var items: List<String> = arrayListOf()
) : Serializable