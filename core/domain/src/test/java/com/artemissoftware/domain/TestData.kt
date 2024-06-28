package com.artemissoftware.domain

import com.artemissoftware.models.Product
import java.util.Locale

internal object TestData {

    val productName = "my product"
    private val likes = 119
    val productPrice = likes.toDouble()
    val tags = "egg, eggs, dinosaur"
    val previewURL = "https://cdn.pixabay.com/photo/2015/12/26/17/08/egg-1108880_150.jpg"
    val comments = "my comment"

    fun getProduct(
        id: Int = 1108880,
        name: String? = null,
        tagList: String = tags,
        price: Double = productPrice,
        quantity: Int = 1,
        commentary: String = ""
    ): Product {
        return Product(
            id = id,
            title = (name ?: tagList.split(",")[0]).replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            },
            imageUrl = previewURL,
            quantity = quantity,
            price = price,
            description = tagList,
            comments = commentary,
        )
    }
}