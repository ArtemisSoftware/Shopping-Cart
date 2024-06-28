package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.repository.CartRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(id: Int) = cartRepository.getProduct(id = id)
}