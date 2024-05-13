package com.artemissoftware.shoppingcart.domain.usecases

import com.artemissoftware.shoppingcart.domain.repository.CartRepository

class GetCartUseCase constructor(private val cartRepository: CartRepository) {

    operator fun invoke() = cartRepository.getCart()
}