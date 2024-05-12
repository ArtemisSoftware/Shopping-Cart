package com.artemissoftware.shoppingcart.data.network

import com.artemissoftware.shoppingcart.data.network.dto.ImagesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String,
    ): ImagesDto

    companion object{
        const val BASE_URL = "https://pixabay.com"
    }
}