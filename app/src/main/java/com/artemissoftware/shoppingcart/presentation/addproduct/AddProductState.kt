package com.artemissoftware.shoppingcart.presentation.addproduct

import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.domain.models.SnackBarState

data class AddProductState(
    val product: Product? = null,
    val snackBarState: SnackBarState? = null
)
