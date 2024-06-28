package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.repository.CartRepository
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(searchQuery: String) = cartRepository.searchProducts(searchQuery = searchQuery)
}