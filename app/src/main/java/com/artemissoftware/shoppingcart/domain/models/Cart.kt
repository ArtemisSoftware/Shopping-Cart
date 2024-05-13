package com.artemissoftware.shoppingcart.domain.models

data class Cart(
    val products: List<Product> = emptyList(),
    val total: Double = 0.0
)
