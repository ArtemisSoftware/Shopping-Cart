package com.artemissoftware.shoppingcart.data.remote

import com.artemissoftware.shoppingcart.data.remote.dto.ImagesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String,
    ): ImagesDto
}
