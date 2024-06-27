package com.artemissoftware.network.source

import com.artemissoftware.network.HandleApi
import com.artemissoftware.network.PixabayApi
import com.artemissoftware.network.dto.ImagesDto
import javax.inject.Inject


class PixabayApiSource @Inject constructor(private val pixabayApi: PixabayApi) {

    suspend fun getImages(searchQuery: String): ImagesDto {
        return HandleApi.safeApiCall {
            pixabayApi.searchForImage(searchQuery = searchQuery)
        }
    }

    suspend fun getImageById(id: String): ImagesDto{
        return HandleApi.safeApiCall {
            pixabayApi.searchForImageById(id = id)
        }
    }
}