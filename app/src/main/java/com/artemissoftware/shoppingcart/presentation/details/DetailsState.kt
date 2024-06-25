package com.artemissoftware.shoppingcart.presentation.details

import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.domain.models.SnackBarState

data class DetailsState(
    val product: Product? = null,
    val snackBarState: SnackBarState? = null
)
