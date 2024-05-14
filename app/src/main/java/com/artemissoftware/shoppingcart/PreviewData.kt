package com.artemissoftware.shoppingcart

import com.artemissoftware.shoppingcart.domain.models.Cart
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.presentation.addproduct.AddProductState
import com.artemissoftware.shoppingcart.presentation.cart.CartState
import com.artemissoftware.shoppingcart.presentation.searchproduct.SearchProductState

object PreviewData {

    val product =
        Product(
            id = 1,
            title = "Office Code",
            quantity = 2,
            price =234.0,
            imageUrl = "",
            description = "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since. " +
                    "When an unknown printer took a galley.",
            img = R.drawable.office_code)

    val cart = Cart(listOf(product, product), 20.0)

    val cartState = CartState(cart = cart)
    val addProductState = AddProductState(product = product)
    val searchProductState = SearchProductState(products = listOf(product, product),  searchQuery = "query")
}