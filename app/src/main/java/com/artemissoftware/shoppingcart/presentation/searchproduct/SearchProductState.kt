package com.artemissoftware.shoppingcart.presentation.searchproduct

import com.artemissoftware.shoppingcart.domain.models.Product

data class SearchProductState(
    val products: List<Product> = emptyList()
)