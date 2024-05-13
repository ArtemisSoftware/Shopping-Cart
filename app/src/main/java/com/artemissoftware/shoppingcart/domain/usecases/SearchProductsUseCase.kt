package com.artemissoftware.shoppingcart.domain.usecases

import com.artemissoftware.shoppingcart.domain.repository.CartRepository

class SearchProductsUseCase constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(searchQuery: String) = cartRepository.searchProducts(searchQuery = searchQuery)
}