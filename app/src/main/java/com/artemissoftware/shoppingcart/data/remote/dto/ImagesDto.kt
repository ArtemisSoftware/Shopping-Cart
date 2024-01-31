package com.artemissoftware.shoppingcart.data.remote.dto

import com.squareup.moshi.Json

data class ImagesDto(
    @Json(name = "hits")
    val hits: List<HitDto> = emptyList(),
    @Json(name = "total")
    val total: Int,
    @Json(name = "totalHits")
    val totalHits: Int,
)
