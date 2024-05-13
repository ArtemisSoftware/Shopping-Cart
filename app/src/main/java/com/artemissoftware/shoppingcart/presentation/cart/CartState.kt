package com.artemissoftware.shoppingcart.presentation.cart

import com.artemissoftware.shoppingcart.domain.models.Cart
import com.artemissoftware.shoppingcart.domain.models.Product

data class CartState(
    val cart: Cart = Cart()
)
