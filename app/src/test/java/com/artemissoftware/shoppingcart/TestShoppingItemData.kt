package com.artemissoftware.shoppingcart

import com.artemissoftware.shoppingcart.domain.models.ShoppingItem

object TestShoppingItemData {

    val shoppingItemEntity = ShoppingItemEntity(name = "Armour", amount = 1, price = 100f, imageUrl = "url", id = 1)
    val shoppingItem = ShoppingItem(name = "Armour", amount = 1, price = 100f, imageUrl = "url", id = 1)

    val shoppingItemList = listOf(
        ShoppingItemEntity(name = "Gold Armour", amount = 1, price = 1000f, imageUrl = "url", id = 1),
        ShoppingItemEntity(name = "Bronze Armour", amount = 2, price = 2000f, imageUrl = "url", id = 2),
        ShoppingItemEntity(name = "Silver Armour", amount = 1, price = 3000f, imageUrl = "url", id = 3),
    )
}
