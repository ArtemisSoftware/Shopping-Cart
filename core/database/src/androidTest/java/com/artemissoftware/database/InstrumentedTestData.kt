package com.artemissoftware.database

import com.artemissoftware.database.entities.ProductEntity

internal object InstrumentedTestData {

    val id = 1108880
    val previewURL = "https://cdn.pixabay.com/photo/2015/12/26/17/08/egg-1108880_150.jpg"
    val productName = "Egg"
    val productPrice = 100.0
    val tags = "egg, eggs, dinosaur"
    val commentary = "no comments"

    val productEntity = ProductEntity(
        id = 1,
        imageUrl = previewURL,
        amount = 1,
        description = tags,
        name = productName.capitalize(),
        price = productPrice,
        commentary = commentary
    )
}