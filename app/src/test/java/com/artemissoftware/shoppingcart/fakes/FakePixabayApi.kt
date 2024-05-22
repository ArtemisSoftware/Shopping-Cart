package com.artemissoftware.shoppingcart.fakes

import com.artemissoftware.shoppingcart.ProductTestData
import com.artemissoftware.shoppingcart.ProductTestData.multipleImagesDto
import com.artemissoftware.shoppingcart.data.network.PixabayApi
import com.artemissoftware.shoppingcart.data.network.dto.ImagesDto

class FakePixabayApi : PixabayApi {

    var shouldReturnError: Boolean = false
    private val images = multipleImagesDto

    override suspend fun searchForImage(searchQuery: String): ImagesDto {
        if(shouldReturnError){
            throw RuntimeException()
        }

        val result = images.hits.filter { it.tags.contains(searchQuery) }

        return ImagesDto(
            total = result.size,
            totalHits = result.size,
            hits = result
        )
    }

    override suspend fun searchForImageById(id: String): ImagesDto {
        if(shouldReturnError){
            throw RuntimeException()
        }

        val result = images.hits.filter { it.id == id.toInt() }

        return ImagesDto(
            total = result.size,
            totalHits = result.size,
            hits = result
        )
    }
}