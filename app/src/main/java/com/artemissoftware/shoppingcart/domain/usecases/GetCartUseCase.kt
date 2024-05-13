package com.artemissoftware.shoppingcart.domain.usecases

import com.artemissoftware.shoppingcart.domain.models.Cart
import com.artemissoftware.shoppingcart.domain.repository.CartRepository
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val cartRepository: CartRepository) {

    operator fun invoke() = cartRepository.getCart().combine(cartRepository.getCartTotal()) { products, total ->
        Cart(
            products = products,
            total = total
        )
    }
}