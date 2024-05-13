package com.artemissoftware.shoppingcart.domain

class SearchProductsUseCase constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(searchQuery: String) = cartRepository.searchProducts(searchQuery = searchQuery)
}