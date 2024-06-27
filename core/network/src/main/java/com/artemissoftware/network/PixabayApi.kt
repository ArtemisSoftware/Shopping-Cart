package com.artemissoftware.network

import com.artemissoftware.network.dto.ImagesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
    ): ImagesDto

    @GET("/api/")
    suspend fun searchForImageById(
        @Query("id") id: String,
    ): ImagesDto

    companion object{
        const val BASE_URL = "https://pixabay.com"
    }
}