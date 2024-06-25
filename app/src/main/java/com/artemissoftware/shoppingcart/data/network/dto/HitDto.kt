package com.artemissoftware.shoppingcart.data.network.dto

import com.squareup.moshi.Json

data class HitDto(
    @field:Json(name = "comments")
    val comments: Int,
    @field:Json(name = "downloads")
    val downloads: Int,
    @field:Json(name = "fullHDURL")
    val fullHDURL: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "imageHeight")
    val imageHeight: Int,
    @field:Json(name = "imageSize")
    val imageSize: Int,
    @field:Json(name = "imageURL")
    val imageURL: String? = null,
    @field:Json(name = "imageWidth")
    val imageWidth: Int,
    @field:Json(name = "largeImageURL")
    val largeImageURL: String,
    @field:Json(name = "likes")
    val likes: Int,
    @field:Json(name = "pageURL")
    val pageURL: String,
    @field:Json(name = "previewHeight")
    val previewHeight: Int,
    @field:Json(name = "previewURL")
    val previewURL: String,
    @field:Json(name = "previewWidth")
    val previewWidth: Int,
    @field:Json(name = "tags")
    val tags: String,
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "user")
    val user: String,
    @field:Json(name = "user_id")
    val userId: Int,
    @field:Json(name = "userImageURL")
    val userImageURL: String,
    @field:Json(name = "views")
    val views: Int,
    @field:Json(name = "webformatHeight")
    val webFormatHeight: Int,
    @field:Json(name = "webformatURL")
    val webFormatURL: String,
    @field:Json(name = "webformatWidth")
    val webFormatWidth: Int,
)
