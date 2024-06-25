package com.artemissoftware.shoppingcart.domain.usecases

import com.artemissoftware.shoppingcart.domain.repository.CartRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(id: Int) = cartRepository.getProduct(id = id)
}