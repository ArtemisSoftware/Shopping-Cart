package com.artemissoftware.shoppingcart.data.network.source

import com.artemissoftware.shoppingcart.data.network.HandleApi
import com.artemissoftware.shoppingcart.data.network.PixabayApi
import com.artemissoftware.shoppingcart.data.network.dto.ImagesDto

class PixabayApiSource constructor(private val pixabayApi: PixabayApi) {

    suspend fun getImages(searchQuery: String): ImagesDto{
        return HandleApi.safeApiCall {
            pixabayApi.searchForImage(searchQuery = searchQuery)
        }
    }
}