package com.artemissoftware.shoppingcart.domain.usecases

import com.artemissoftware.shoppingcart.domain.repository.CartRepository
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(searchQuery: String) = cartRepository.searchProducts(searchQuery = searchQuery)
}