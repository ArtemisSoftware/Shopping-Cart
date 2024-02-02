package com.artemissoftware.shoppingcart.data.mappers

import com.artemissoftware.shoppingcart.data.database.ShoppingItemEntity
import com.artemissoftware.shoppingcart.domain.models.ShoppingItem

fun ShoppingItem.toEntity() = ShoppingItemEntity(
    name = name,
    amount = amount,
    price = price,
    imageUrl = imageUrl,
    id = id,
)
