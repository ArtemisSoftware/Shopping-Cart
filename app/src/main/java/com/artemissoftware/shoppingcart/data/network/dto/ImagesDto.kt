package com.artemissoftware.shoppingcart.data.network.dto

import com.squareup.moshi.Json

data class ImagesDto(
    @field:Json(name = "hits")
    val hits: List<HitDto> = emptyList(),
    @field:Json(name = "total")
    val total: Int,
    @field:Json(name = "totalHits")
    val totalHits: Int,
)