package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.repository.CartRepository
import com.artemissoftware.models.Cart
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