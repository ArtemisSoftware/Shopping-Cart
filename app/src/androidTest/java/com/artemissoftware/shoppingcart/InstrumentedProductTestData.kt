package com.artemissoftware.shoppingcart

import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity
import com.artemissoftware.shoppingcart.domain.models.Product

internal object InstrumentedProductTestData{

    val id = 1108880
    val previewURL = "https://cdn.pixabay.com/photo/2015/12/26/17/08/egg-1108880_150.jpg"
    val productName = "Egg"
    val productPrice = 100.0
    val tags = "egg, eggs, dinosaur"
    val commentary = "no comments"


    val product_Entity = ProductEntity(
        id = id,
        imageUrl = previewURL,
        amount = 1,
        description = tags,
        name = productName,
        price = productPrice,
        commentary = commentary
    )

    val productV1 = Product(
        id = id,
        title = productName,
        quantity = 1,
        price =productPrice,
        imageUrl = previewURL,
        description = tags
    )













    //-----

    val productEntity = ProductEntity(
        id = 1,
        imageUrl = previewURL,
        amount = 1,
        description = tags,
        name = productName.capitalize(),
        price = productPrice,
        commentary = commentary
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

    val product = Product(
            id = 1,
            title = "Office Code",
            quantity = 2,
            price =234.0,
            imageUrl = "",
            description = "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since. " +
                    "When an unknown printer took a galley."
        )


}