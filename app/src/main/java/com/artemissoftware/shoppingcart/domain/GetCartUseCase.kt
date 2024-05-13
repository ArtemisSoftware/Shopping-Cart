package com.artemissoftware.shoppingcart.domain

import com.artemissoftware.shoppingcart.domain.models.Product
import kotlinx.coroutines.flow.flowOf

class GetCartUseCase constructor(private val cartRepository: CartRepository) {

    operator fun invoke() = cartRepository.getCart()
}