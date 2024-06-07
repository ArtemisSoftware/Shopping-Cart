package com.artemissoftware.shoppingcart

import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity

internal object InstrumentedProductTestData{

    val productName = "my product"
    val productPrice = 100.0
    val tags = "egg, eggs, dinosaur"
    val previewURL = "https://cdn.pixabay.com/photo/2015/12/26/17/08/egg-1108880_150.jpg"

    val productEntity = ProductEntity(
        id = 1,
        imageUrl = previewURL,
        amount = 1,
        description = tags,
        name = productName.capitalize(),
        price = productPrice,
        commentary = "no comments"
    )

    val productEntity2 = ProductEntity(
        id = 2,
        imageUrl = previewURL,
        amount = 1,
        description = tags,
        name = productName.capitalize(),
        price = productPrice,
        commentary = "no comments"
    )

    val productEntities = listOf(productEntity, productEntity2)
}