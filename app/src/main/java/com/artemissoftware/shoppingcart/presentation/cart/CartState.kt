package com.artemissoftware.shoppingcart.presentation.cart

import com.artemissoftware.shoppingcart.domain.models.Product

data class CartState(
    val products: List<Product> = emptyList()
){
    fun total() = products.sumOf { it.quantity * it.price }
}
