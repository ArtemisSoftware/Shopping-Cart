package com.artemissoftware.product.searchproduct

import com.artemissoftware.models.Product
import com.artemissoftware.models.SnackBarState

data class SearchProductState(
    val searchQuery: String = "",
    val products: List<Product> = emptyList(),
    val snackBarState: SnackBarState? = null
)