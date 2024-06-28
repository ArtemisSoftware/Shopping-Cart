package com.artemissoftware.product.addproduct

import com.artemissoftware.models.Product
import com.artemissoftware.models.SnackBarState

data class AddProductState(
    val product: Product? = null,
    val snackBarState: SnackBarState? = null
)
