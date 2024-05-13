package com.artemissoftware.shoppingcart.domain.usecases

import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.domain.repository.CartRepository

class SaveProductUseCase constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(product: Product) = cartRepository.saveProduct(product = product)
}