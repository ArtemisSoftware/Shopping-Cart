package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.repository.CartRepository
import com.artemissoftware.models.Product
import javax.inject.Inject

class SaveProductUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(product: Product) = cartRepository.saveProduct(product = product)
}