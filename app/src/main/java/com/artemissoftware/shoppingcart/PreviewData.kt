package com.artemissoftware.shoppingcart

import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.presentation.additem.AddProductState
import com.artemissoftware.shoppingcart.presentation.cart.CartState

object PreviewData {

    val product =
        Product(
            title = "Office Code",
            quantity = 2,
            price =234.0,
            "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since. " +
                    "When an unknown printer took a galley.",
            R.drawable.office_code)

    val cartState = CartState(products = listOf(product, product))
    val addProductState = AddProductState(product = product)
}