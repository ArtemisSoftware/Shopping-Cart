package com.artemissoftware.shoppingcart.domain.models

data class ShoppingItem(
    var name: String,
    var amount: Int,
    var price: Float,
    var imageUrl: String,
    val id: Int = 0,
)
