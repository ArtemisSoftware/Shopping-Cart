package com.artemissoftware.shoppingcart.domain.usecases

import com.artemissoftware.shoppingcart.domain.models.ShoppingItem
import com.artemissoftware.shoppingcart.domain.repositories.ShoppingCartRepository

class InsertShoppingItemUseCase constructor(
    private val shoppingCartRepository: ShoppingCartRepository,
) {

    suspend operator fun invoke(shoppingItem: ShoppingItem) {
        shoppingCartRepository.insertShoppingItem(shoppingItem)
    }
}
