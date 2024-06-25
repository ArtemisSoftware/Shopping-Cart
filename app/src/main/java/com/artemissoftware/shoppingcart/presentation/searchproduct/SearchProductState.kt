package com.artemissoftware.shoppingcart.presentation.searchproduct

import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.domain.models.SnackBarState

data class SearchProductState(
    val searchQuery: String = "",
    val products: List<Product> = emptyList(),
    val snackBarState: SnackBarState? = null
)