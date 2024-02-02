package com.artemissoftware.shoppingcart.domain.repositories

import com.artemissoftware.shoppingcart.domain.models.ShoppingItem

interface ShoppingCartRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)
}
